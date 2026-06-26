package com.example.rentrommSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class utilityRateRequest {
    private Long utilityTypeId;
    private BigDecimal unitPrice;
    private LocalDate effectiveFrom;
}
