package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.BillingTypeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class utilityTypeResponse {
    private Long utilityTypeId;
    private String utilityTypeName;
    private BillingTypeStatus billingTypeStatus;
    private Long unitId;
    private LocalDateTime createdDate;


}
