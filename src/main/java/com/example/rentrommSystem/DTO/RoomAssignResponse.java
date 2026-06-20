package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.AssignStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAssignResponse {
    private Long id;
    private Long tenantId;
    private String tenantName;
    private Long roomId;
    private String roomNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    private AssignStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
