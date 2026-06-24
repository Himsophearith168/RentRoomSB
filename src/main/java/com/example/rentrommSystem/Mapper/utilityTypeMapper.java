package com.example.rentrommSystem.Mapper;

import com.example.rentrommSystem.DTO.utilityTypeRequest;
import com.example.rentrommSystem.DTO.utilityTypeResponse;
import com.example.rentrommSystem.Model.utilityTypesModel;
import org.springframework.stereotype.Component;

@Component
public class utilityTypeMapper {
    public utilityTypesModel toEntity(utilityTypeRequest dto){
        if(dto == null){
            return null;
        }
        return utilityTypesModel.builder()
                .utilityTypeName(dto.getUtilityTypeName())
                .billingTypeStatus(dto.getBillingTypeStatus())
                .unit(dto.getUnit())
                .build();
    }
    public utilityTypeResponse toResponse(utilityTypesModel response){
        if(response == null){
            return null;
        }
        return new utilityTypeResponse(
                response.getUtilityTypeId(),
                response.getUtilityTypeName(),
                response.getBillingTypeStatus(),
                response.getUnit(),
                response.getCreatedDate()
        );
    };
}
