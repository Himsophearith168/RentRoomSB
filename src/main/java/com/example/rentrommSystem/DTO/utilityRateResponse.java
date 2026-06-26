package com.example.rentrommSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class utilityRateResponse {
    private Integer rateId;
    private Long utilityTypeId;
    private String utilityTypeName;
    private BigDecimal unitPrice;
    private LocalDate effectiveFrom;
    private Boolean isActive;
}
