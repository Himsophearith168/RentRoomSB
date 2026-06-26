package com.example.rentrommSystem.Controller;

import com.example.rentrommSystem.DTO.BillDetailResponse;
import com.example.rentrommSystem.Service.BillDetailService;
import com.example.rentrommSystem.Util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/billDetails")
public class BillDetailsController {

    private final BillDetailService billDetailService;

    @GetMapping
    public ResponseEntity<APIResponse<List<BillDetailResponse>>> getAllBillDetails() {
        List<BillDetailResponse> response = billDetailService.findAll();
        return ResponseEntity.ok(
                APIResponse.<List<BillDetailResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Get All BillDetails Successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponse<BillDetailResponse>> getBillDetail(@PathVariable Long id) {
        BillDetailResponse response = billDetailService.findById(id);
        return ResponseEntity.ok(
                APIResponse.<BillDetailResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Retrieved data Successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("bill/{billId}")
    public ResponseEntity<APIResponse<List<BillDetailResponse>>> getBillDetailsByBill(@PathVariable Long billId) {
        List<BillDetailResponse> response = billDetailService.findByBill(billId);
        return ResponseEntity.ok(
                APIResponse.<List<BillDetailResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Get BillDetails By Bill Successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<Void>> deleteBillDetail(@PathVariable Long id) {
        billDetailService.deleteBillDetail(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Delete Successfully")
                        .build()
        );
    }
}
