package com.example.rentrommSystem.Mapper;

import com.example.rentrommSystem.DTO.BillDetailRequest;
import com.example.rentrommSystem.DTO.BillDetailResponse;
import com.example.rentrommSystem.Model.BillDetailModel;
import org.springframework.stereotype.Component;

@Component
public class BillDetailMapper {

    public BillDetailModel toEntity(BillDetailRequest dto) {
        if (dto == null) {
            return null;
        }
        return BillDetailModel.builder()
                .oldReading(dto.getOldReading())
                .newReading(dto.getNewReading())
                .quantity(dto.getQuantity())
                .unitPrice(dto.getUnitPrice())
                .notes(dto.getNotes())
                .build();
    }

    public BillDetailResponse toResponse(BillDetailModel entity) {
        if (entity == null) {
            return null;
        }
        BillDetailResponse response = new BillDetailResponse();
        response.setDetailId(entity.getDetailId());
        response.setOldReading(entity.getOldReading());
        response.setNewReading(entity.getNewReading());
        response.setQuantity(entity.getQuantity());
        response.setUnitPrice(entity.getUnitPrice());
        response.setTotalPrice(entity.getTotalPrice());
        response.setNotes(entity.getNotes());
        if (entity.getUtilityType() != null) {
            response.setUtilityTypeId(entity.getUtilityType().getUtilityTypeId());
            response.setUtilityTypeName(entity.getUtilityType().getUtilityTypeName());
        }
        return response;
    }
}
