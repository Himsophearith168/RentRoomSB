package com.example.rentrommSystem.Controller;

import com.example.rentrommSystem.DTO.BillRequest;
import com.example.rentrommSystem.DTO.BillResponse;
import com.example.rentrommSystem.Service.BillService;
import com.example.rentrommSystem.Util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bills")
public class BillsController {

    private final BillService billService;

    @GetMapping
    public ResponseEntity<APIResponse<List<BillResponse>>> getAllBills() {
        List<BillResponse> response = billService.findAll();
        return ResponseEntity.ok(
                APIResponse.<List<BillResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Get All Bills Successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponse<BillResponse>> getBill(@PathVariable Long id) {
        BillResponse response = billService.findById(id);
        return ResponseEntity.ok(
                APIResponse.<BillResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Retrieved data Successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("assignment/{assignmentId}")
    public ResponseEntity<APIResponse<List<BillResponse>>> getBillsByAssignment(@PathVariable Long assignmentId) {
        List<BillResponse> response = billService.findByRoomAssignment(assignmentId);
        return ResponseEntity.ok(
                APIResponse.<List<BillResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Get Bills By Assignment Successfully")
                        .data(response)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<APIResponse<BillResponse>> saveBill(@RequestBody BillRequest request) {
        BillResponse response = billService.createBill(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.<BillResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Create New Bill Successfully")
                        .data(response)
                        .build()
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<APIResponse<BillResponse>> updateBill(@PathVariable Long id, @RequestBody BillRequest request) {
        BillResponse response = billService.updateBill(id, request);
        return ResponseEntity.ok(
                APIResponse.<BillResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Update Successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<Void>> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Delete Successfully")
                        .build()
        );
    }
}
