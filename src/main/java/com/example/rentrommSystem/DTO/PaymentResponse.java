package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Long paymentId;
    private Long billId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private LocalDateTime paymentDate;
    private String proofImage;
    private String remarks;
    private LocalDateTime createdAt;
}
