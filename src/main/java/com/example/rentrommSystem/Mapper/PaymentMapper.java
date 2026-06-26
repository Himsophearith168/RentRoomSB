package com.example.rentrommSystem.Mapper;

import com.example.rentrommSystem.DTO.PaymentRequest;
import com.example.rentrommSystem.DTO.PaymentResponse;
import com.example.rentrommSystem.Model.PaymentModel;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentModel toEntity(PaymentRequest dto) {
        if (dto == null) {
            return null;
        }
        return PaymentModel.builder()
                .amount(dto.getAmount())
                .paymentMethod(dto.getPaymentMethod())
                .proofImage(dto.getProofImage())
                .remarks(dto.getRemarks())
                .build();
    }

    public PaymentResponse toResponse(PaymentModel entity) {
        if (entity == null) {
            return null;
        }
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(entity.getPaymentId());
        response.setAmount(entity.getAmount());
        response.setPaymentMethod(entity.getPaymentMethod());
        response.setPaymentDate(entity.getPaymentDate());
        response.setProofImage(entity.getProofImage());
        response.setRemarks(entity.getRemarks());
        response.setCreatedAt(entity.getCreatedAt());
        if (entity.getBill() != null) {
            response.setBillId(entity.getBill().getBillId());
        }
        return response;
    }
}
