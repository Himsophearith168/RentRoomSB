package com.example.rentrommSystem.Mapper;

import com.example.rentrommSystem.DTO.TenantRequest;
import com.example.rentrommSystem.DTO.TenantResponse;
import com.example.rentrommSystem.Model.TenantModel;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper {
    public TenantModel toEntity(TenantRequest tenantRequest) {
        if (tenantRequest == null) {
            return null;
        }
        return TenantModel.builder()
                .tenantName(tenantRequest.getTenantName())
                .status(tenantRequest.getStatus())
                .phoneNumber(tenantRequest.getPhoneNumber())
                .idCard(tenantRequest.getIdCard())
                .address(tenantRequest.getAddress())
                .build();

    }
    public TenantResponse  toResponse(TenantModel Entity) {
        if (Entity == null) {
            return null;
        }
        return new TenantResponse(
                Entity.getId(),
                Entity.getTenantName(),
                Entity.getStatus(),
                Entity.getPhoneNumber(),
                Entity.getIdCard(),
                Entity.getAddress()
        );
    }
}
