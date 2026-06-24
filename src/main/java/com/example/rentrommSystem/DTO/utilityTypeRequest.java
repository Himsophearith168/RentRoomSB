package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.BillingTypeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class utilityTypeRequest {
    private String utilityTypeName;
    private BillingTypeStatus  billingTypeStatus;
    private Long unit;
}
