package com.example.rentrommSystem.Controller;

import com.example.rentrommSystem.DTO.TenantRequest;
import com.example.rentrommSystem.DTO.TenantResponse;
import com.example.rentrommSystem.Service.TenantService;
import com.example.rentrommSystem.Util.APIResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @GetMapping
    public ResponseEntity<APIResponse<List<TenantResponse>>> getAllTenant(){
        List<TenantResponse> tenantResponseList = tenantService.getTenantList();
        return ResponseEntity.ok(
                APIResponse.<List<TenantResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Tenants Retrieved successfully")
                        .data(tenantResponseList)
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<TenantResponse>> getTenantById(@PathVariable Long id) {
        TenantResponse tenantResponse = tenantService.getTenantById(id);
        return ResponseEntity.ok(
                APIResponse.<TenantResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Tenant retrieved successfully")
                        .data(tenantResponse)
                        .build()
        );
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse<TenantResponse>> createTenant(@ModelAttribute @Valid TenantRequest tenantRequest) {
        TenantResponse tenantResponse = tenantService.createTenant(tenantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.<TenantResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Tenant created successfully")
                        .data(tenantResponse)
                        .build()
        );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse<TenantResponse>> updateTenant(
            @PathVariable Long id,
            @ModelAttribute @Valid TenantRequest tenantRequest) {
        TenantResponse tenantResponse = tenantService.updateTenant(id, tenantRequest);
        return ResponseEntity.ok(
                APIResponse.<TenantResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Tenant updated successfully")
                        .data(tenantResponse)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Tenant deleted successfully")
                        .build()
        );
    }
}
