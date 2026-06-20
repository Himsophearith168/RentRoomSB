package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.AssignStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAssignRequest {
    @NotNull(message = "Tenant ID is required")
    private Long tenantId;

    @NotNull(message = "Room ID is required")
    private Long roomId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    private AssignStatus status;
}
