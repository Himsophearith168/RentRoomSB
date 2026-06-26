package com.example.rentrommSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailResponse {
    private Long detailId;
    private Long utilityTypeId;
    private String utilityTypeName;
    private BigDecimal oldReading;
    private BigDecimal newReading;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String notes;
}
