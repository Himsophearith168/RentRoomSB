package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Long billId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String proofImage;
    private String remarks;
}
