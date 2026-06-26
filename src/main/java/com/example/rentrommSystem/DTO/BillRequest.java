package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillRequest {
    private Long assignmentId;
    private LocalDate billMonth;
    private BigDecimal roomRent;
    private BigDecimal otherFee;
    private BillStatus status;
    private LocalDate dueDate;
    private List<BillDetailRequest> billDetails;
}
