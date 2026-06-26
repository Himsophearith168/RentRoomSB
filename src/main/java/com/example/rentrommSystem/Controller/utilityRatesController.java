package com.example.rentrommSystem.Controller;

import com.example.rentrommSystem.DTO.utilityRateRequest;
import com.example.rentrommSystem.DTO.utilityRateResponse;
import com.example.rentrommSystem.Service.utilityRateService;
import com.example.rentrommSystem.Util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/utilityRates")
public class utilityRatesController {

    private final utilityRateService utilityRateService;

    @GetMapping
    public ResponseEntity<APIResponse<List<utilityRateResponse>>> getAllUtilityRates() {
        List<utilityRateResponse> response = utilityRateService.findAll();
        return ResponseEntity.ok(
                APIResponse.<List<utilityRateResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Get All UtilityRates Successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponse<utilityRateResponse>> getUtilityRate(@PathVariable Integer id) {
        utilityRateResponse response = utilityRateService.findById(id);
        return ResponseEntity.ok(
                APIResponse.<utilityRateResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Retrieved data Successfully")
                        .data(response)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<APIResponse<utilityRateResponse>> saveUtilityRate(@RequestBody utilityRateRequest request) {
        utilityRateResponse response = utilityRateService.createUtilityRate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.<utilityRateResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Create New UtilityRate Successfully")
                        .data(response)
                        .build()
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<APIResponse<utilityRateResponse>> updateUtilityRate(@PathVariable Integer id, @RequestBody utilityRateRequest request) {
        utilityRateResponse response = utilityRateService.updateUtilityRate(id, request);
        return ResponseEntity.ok(
                APIResponse.<utilityRateResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Update Successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<Void>> deleteUtilityRate(@PathVariable Integer id) {
        utilityRateService.deleteUtilityRate(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Delete Successfully")
                        .build()
        );
    }
}
