package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.GenderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantResponse {
    private Long id;
    private String tenantName;
    private GenderStatus status;
    private Integer phoneNumber;
    private String idCard;
    private String address;

}
