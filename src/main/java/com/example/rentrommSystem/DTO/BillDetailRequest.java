package com.example.rentrommSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailRequest {
    private Long utilityTypeId;
    private BigDecimal oldReading;
    private BigDecimal newReading;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private String notes;
}
