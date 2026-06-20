package com.example.rentrommSystem.Mapper;

import com.example.rentrommSystem.DTO.RoomAssignResponse;
import com.example.rentrommSystem.Model.RoomAssignModel;
import org.springframework.stereotype.Component;

@Component
public class RoomAssignMapper {
    public RoomAssignResponse toResponse(RoomAssignModel assignment) {
        if (assignment == null) {
            return null;
        }
        return new RoomAssignResponse(
                assignment.getId(),
                assignment.getTenant() != null ? assignment.getTenant().getId() : null,
                assignment.getTenantName(),
                assignment.getRoom() != null ? assignment.getRoom().getId() : null,
                assignment.getRoom() != null ? assignment.getRoom().getRoomNumber() : null,
                assignment.getStartDate() != null ? assignment.getStartDate().toLocalDate() : null,
                assignment.getStatus(),
                assignment.getCreatedAt(),
                assignment.getUpdatedAt()
        );
    }
}
