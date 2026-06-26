package com.example.rentrommSystem.Mapper;

import com.example.rentrommSystem.DTO.utilityRateRequest;
import com.example.rentrommSystem.DTO.utilityRateResponse;
import com.example.rentrommSystem.Model.utilityRatesModel;
import org.springframework.stereotype.Component;

@Component
public class utilityRateMapper {

    public utilityRatesModel toEntity(utilityRateRequest dto) {
        if (dto == null) {
            return null;
        }
        return utilityRatesModel.builder()
                .unitPrice(dto.getUnitPrice())
                .effectiveFrom(dto.getEffectiveFrom())
                .isActive(true)
                .build();
    }

    public utilityRateResponse toResponse(utilityRatesModel entity) {
        if (entity == null) {
            return null;
        }
        utilityRateResponse response = new utilityRateResponse();
        response.setRateId(entity.getRateId());
        response.setUnitPrice(entity.getUnitPrice());
        response.setEffectiveFrom(entity.getEffectiveFrom());
        response.setIsActive(entity.getIsActive());
        if (entity.getUtilityType() != null) {
            response.setUtilityTypeId(entity.getUtilityType().getUtilityTypeId());
            response.setUtilityTypeName(entity.getUtilityType().getUtilityTypeName());
        }
        return response;
    }
}
