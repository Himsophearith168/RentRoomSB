package com.example.rentrommSystem.Controller;

import com.example.rentrommSystem.DTO.PaymentRequest;
import com.example.rentrommSystem.DTO.PaymentResponse;
import com.example.rentrommSystem.Service.PaymentService;
import com.example.rentrommSystem.Util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/payments")
public class PaymentsController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<APIResponse<List<PaymentResponse>>> getAllPayments() {
        List<PaymentResponse> response = paymentService.findAll();
        return ResponseEntity.ok(
                APIResponse.<List<PaymentResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Get All Payments Successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponse<PaymentResponse>> getPayment(@PathVariable Long id) {
        PaymentResponse response = paymentService.findById(id);
        return ResponseEntity.ok(
                APIResponse.<PaymentResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Retrieved data Successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("bill/{billId}")
    public ResponseEntity<APIResponse<List<PaymentResponse>>> getPaymentsByBill(@PathVariable Long billId) {
        List<PaymentResponse> response = paymentService.findByBill(billId);
        return ResponseEntity.ok(
                APIResponse.<List<PaymentResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Get Payments By Bill Successfully")
                        .data(response)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<APIResponse<PaymentResponse>> savePayment(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.createPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.<PaymentResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Record Payment Successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<Void>> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Delete Payment and Recalculated Bill Status Successfully")
                        .build()
        );
    }
}
