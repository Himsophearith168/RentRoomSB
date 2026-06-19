package com.example.rentrommSystem.Mapper;

import com.example.rentrommSystem.DTO.RoomRequest;
import com.example.rentrommSystem.DTO.RoomResponse;
import com.example.rentrommSystem.Model.RoomModel;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public RoomModel toEntity (RoomRequest Entity){
        if(Entity == null){
            return null;
        }
        return RoomModel.builder()
                .roomNumber(Entity.getRoomNumber())
                .description(Entity.getDescription())
                .price(Entity.getPrice())
                .status(Entity.getStatus())
                .build();
    };
    public RoomResponse toResponse (RoomModel Entity){
        if(Entity == null){
            return null;
        }
        return new RoomResponse(
                Entity.getId(),
                Entity.getRoomNumber(),
                Entity.getDescription(),
                Entity.getPrice(),
                Entity.getStatus(),
                Entity.getCreatedAt(),
                Entity.getUpdatedAt()
        );

    };

}
