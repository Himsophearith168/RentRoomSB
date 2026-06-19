package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.RoomStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoomRequest {
    @NotNull(message = "Room name Or Room Number is Require")
    private String roomNumber;
    private String description;
    @NotNull(message = "The Room Price is Require")
    private Double price;
    private RoomStatus status;
}
