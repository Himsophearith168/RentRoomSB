package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.BillDetailRequest;
import com.example.rentrommSystem.DTO.BillRequest;
import com.example.rentrommSystem.DTO.BillResponse;
import com.example.rentrommSystem.Mapper.BillMapper;
import com.example.rentrommSystem.Model.BillDetailModel;
import com.example.rentrommSystem.Model.BillModel;
import com.example.rentrommSystem.Model.BillStatus;
import com.example.rentrommSystem.Model.RoomAssignModel;
import com.example.rentrommSystem.Model.utilityRatesModel;
import com.example.rentrommSystem.Model.utilityTypesModel;
import com.example.rentrommSystem.Repository.BillRepository;
import com.example.rentrommSystem.Repository.RoomAssignRepository;
import com.example.rentrommSystem.Repository.utilityRateRepository;
import com.example.rentrommSystem.Repository.utilityTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;
    private final RoomAssignRepository roomAssignRepository;
    private final utilityTypeRepository utilityTypeRepository;
    private final utilityRateRepository utilityRateRepository;
    private final BillMapper billMapper;

    public List<BillResponse> findAll() {
        return billRepository.findAll().stream()
                .map(billMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BillResponse findById(Long id) {
        BillModel bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));
        return billMapper.toResponse(bill);
    }

    public List<BillResponse> findByRoomAssignment(Long assignmentId) {
        return billRepository.findByRoomAssignmentId(assignmentId).stream()
                .map(billMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public BillResponse createBill(BillRequest request) {
        RoomAssignModel roomAssignment = roomAssignRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("RoomAssignment not found with id: " + request.getAssignmentId()));

        BigDecimal roomRent = request.getRoomRent();
        if (roomRent == null) {
            if (roomAssignment.getRoom() != null && roomAssignment.getRoom().getPrice() != null) {
                roomRent = BigDecimal.valueOf(roomAssignment.getRoom().getPrice());
            } else {
                roomRent = BigDecimal.ZERO;
            }
        }

        BigDecimal otherFee = request.getOtherFee() != null ? request.getOtherFee() : BigDecimal.ZERO;
        BigDecimal totalAmount = roomRent.add(otherFee);

        BillModel bill = BillModel.builder()
                .roomAssignment(roomAssignment)
                .billMonth(request.getBillMonth())
                .roomRent(roomRent)
                .otherFee(otherFee)
                .status(request.getStatus() != null ? request.getStatus() : BillStatus.Draft)
                .dueDate(request.getDueDate())
                .billDetails(new ArrayList<>())
                .build();

        if (request.getBillDetails() != null && !request.getBillDetails().isEmpty()) {
            for (BillDetailRequest detailRequest : request.getBillDetails()) {
                utilityTypesModel utilityType = utilityTypeRepository.findById(detailRequest.getUtilityTypeId())
                        .orElseThrow(() -> new RuntimeException("UtilityType not found with id: " + detailRequest.getUtilityTypeId()));
                BigDecimal quantity = detailRequest.getQuantity();
                if (quantity == null && detailRequest.getNewReading() != null && detailRequest.getOldReading() != null) {
                    quantity = detailRequest.getNewReading().subtract(detailRequest.getOldReading());
                }
                if (quantity == null) {
                    quantity = BigDecimal.ZERO;
                }

                BigDecimal unitPrice = detailRequest.getUnitPrice();
                if (unitPrice == null) {
                    unitPrice = utilityRateRepository.findFirstByUtilityTypeUtilityTypeIdAndIsActiveTrueOrderByEffectiveFromDesc(utilityType.getUtilityTypeId())
                            .map(utilityRatesModel::getUnitPrice)
                            .orElse(BigDecimal.ZERO);
                }

                BigDecimal totalPrice = quantity.multiply(unitPrice);
                totalAmount = totalAmount.add(totalPrice);

                BillDetailModel detail = BillDetailModel.builder()
                        .bill(bill)
                        .utilityType(utilityType)
                        .oldReading(detailRequest.getOldReading())
                        .newReading(detailRequest.getNewReading())
                        .quantity(quantity)
                        .unitPrice(unitPrice)
                        .totalPrice(totalPrice)
                        .notes(detailRequest.getNotes())
                        .build();

                bill.getBillDetails().add(detail);
            }
        }

        bill.setTotalAmount(totalAmount);
        BillModel savedBill = billRepository.save(bill);
        return billMapper.toResponse(savedBill);
    }

    @Transactional
    public BillResponse updateBill(Long id, BillRequest request) {
        BillModel bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));

        RoomAssignModel roomAssignment = roomAssignRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("RoomAssignment not found with id: " + request.getAssignmentId()));

        bill.setRoomAssignment(roomAssignment);
        bill.setBillMonth(request.getBillMonth());
        bill.setDueDate(request.getDueDate());
        
        if (request.getStatus() != null) {
            bill.setStatus(request.getStatus());
        }

        BigDecimal roomRent = request.getRoomRent();
        if (roomRent == null) {
            if (roomAssignment.getRoom() != null && roomAssignment.getRoom().getPrice() != null) {
                roomRent = BigDecimal.valueOf(roomAssignment.getRoom().getPrice());
            } else {
                roomRent = BigDecimal.ZERO;
            }
        }
        bill.setRoomRent(roomRent);

        BigDecimal otherFee = request.getOtherFee() != null ? request.getOtherFee() : BigDecimal.ZERO;
        bill.setOtherFee(otherFee);

        bill.getBillDetails().clear();

        BigDecimal totalAmount = roomRent.add(otherFee);

        if (request.getBillDetails() != null && !request.getBillDetails().isEmpty()) {
            for (BillDetailRequest detailRequest : request.getBillDetails()) {
                utilityTypesModel utilityType = utilityTypeRepository.findById(detailRequest.getUtilityTypeId())
                        .orElseThrow(() -> new RuntimeException("UtilityType not found with id: " + detailRequest.getUtilityTypeId()));

                BigDecimal quantity = detailRequest.getQuantity();
                if (quantity == null && detailRequest.getNewReading() != null && detailRequest.getOldReading() != null) {
                    quantity = detailRequest.getNewReading().subtract(detailRequest.getOldReading());
                }
                if (quantity == null) {
                    quantity = BigDecimal.ZERO;
                }

                BigDecimal unitPrice = detailRequest.getUnitPrice();
                if (unitPrice == null) {
                    unitPrice = utilityRateRepository.findFirstByUtilityTypeUtilityTypeIdAndIsActiveTrueOrderByEffectiveFromDesc(utilityType.getUtilityTypeId())
                            .map(utilityRatesModel::getUnitPrice)
                            .orElse(BigDecimal.ZERO);
                }

                BigDecimal totalPrice = quantity.multiply(unitPrice);
                totalAmount = totalAmount.add(totalPrice);

                BillDetailModel detail = BillDetailModel.builder()
                        .bill(bill)
                        .utilityType(utilityType)
                        .oldReading(detailRequest.getOldReading())
                        .newReading(detailRequest.getNewReading())
                        .quantity(quantity)
                        .unitPrice(unitPrice)
                        .totalPrice(totalPrice)
                        .notes(detailRequest.getNotes())
                        .build();

                bill.getBillDetails().add(detail);
            }
        }

        bill.setTotalAmount(totalAmount);
        BillModel savedBill = billRepository.save(bill);
        return billMapper.toResponse(savedBill);
    }

    public void deleteBill(Long id) {
        if (!billRepository.existsById(id)) {
            throw new RuntimeException("Bill not found with id: " + id);
        }
        billRepository.deleteById(id);
    }
}
