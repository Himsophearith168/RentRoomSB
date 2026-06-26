package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponse {
    private Long billId;
    private Long assignmentId;
    private String tenantName;
    private String roomNumber;
    private LocalDate billMonth;
    private BigDecimal roomRent;
    private BigDecimal otherFee;
    private BigDecimal totalAmount;
    private BillStatus status;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private List<BillDetailResponse> billDetails;
}
