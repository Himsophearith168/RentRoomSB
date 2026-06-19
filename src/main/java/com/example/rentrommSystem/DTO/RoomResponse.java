package com.example.rentrommSystem.DTO;


import com.example.rentrommSystem.Model.RoomStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    private Long id;
    private String roomNumber;
    private String description;
    private Double price;
    private RoomStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
