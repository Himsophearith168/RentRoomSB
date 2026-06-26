package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.BillDetailResponse;
import com.example.rentrommSystem.Mapper.BillDetailMapper;
import com.example.rentrommSystem.Model.BillDetailModel;
import com.example.rentrommSystem.Repository.BillDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillDetailService {

    private final BillDetailRepository billDetailRepository;
    private final BillDetailMapper billDetailMapper;

    public List<BillDetailResponse> findAll() {
        return billDetailRepository.findAll().stream()
                .map(billDetailMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BillDetailResponse findById(Long id) {
        BillDetailModel detail = billDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BillDetail not found with id: " + id));
        return billDetailMapper.toResponse(detail);
    }

    public List<BillDetailResponse> findByBill(Long billId) {
        return billDetailRepository.findByBillBillId(billId).stream()
                .map(billDetailMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteBillDetail(Long id) {
        if (!billDetailRepository.existsById(id)) {
            throw new RuntimeException("BillDetail not found with id: " + id);
        }
        billDetailRepository.deleteById(id);
    }
}
