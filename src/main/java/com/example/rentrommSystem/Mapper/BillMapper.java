package com.example.rentrommSystem.Mapper;

import com.example.rentrommSystem.DTO.BillRequest;
import com.example.rentrommSystem.DTO.BillResponse;
import com.example.rentrommSystem.Model.BillModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BillMapper {

    private final BillDetailMapper billDetailMapper;

    public BillModel toEntity(BillRequest dto) {
        if (dto == null) {
            return null;
        }
        return BillModel.builder()
                .billMonth(dto.getBillMonth())
                .roomRent(dto.getRoomRent())
                .otherFee(dto.getOtherFee())
                .status(dto.getStatus())
                .dueDate(dto.getDueDate())
                .build();
    }

    public BillResponse toResponse(BillModel entity) {
        if (entity == null) {
            return null;
        }
        BillResponse response = new BillResponse();
        response.setBillId(entity.getBillId());
        response.setBillMonth(entity.getBillMonth());
        response.setRoomRent(entity.getRoomRent());
        response.setOtherFee(entity.getOtherFee());
        response.setTotalAmount(entity.getTotalAmount());
        response.setStatus(entity.getStatus());
        response.setDueDate(entity.getDueDate());
        response.setCreatedAt(entity.getCreatedAt());

        if (entity.getRoomAssignment() != null) {
            response.setAssignmentId(entity.getRoomAssignment().getId());
            response.setTenantName(entity.getRoomAssignment().getTenantName());
            if (entity.getRoomAssignment().getRoom() != null) {
                response.setRoomNumber(entity.getRoomAssignment().getRoom().getRoomNumber());
            }
        }

        if (entity.getBillDetails() != null) {
            response.setBillDetails(entity.getBillDetails().stream()
                    .map(billDetailMapper::toResponse)
                    .collect(Collectors.toList()));
        } else {
            response.setBillDetails(Collections.emptyList());
        }

        return response;
    }
}
