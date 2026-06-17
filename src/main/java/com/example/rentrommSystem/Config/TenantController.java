package com.example.rentrommSystem.Config;


import com.example.rentrommSystem.DTO.TenantResponse;
import com.example.rentrommSystem.Service.TenantService;
import com.example.rentrommSystem.Util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @GetMapping
    public ResponseEntity<APIResponse<List<TenantResponse>>> getAllTenant(){
        List<TenantResponse> tenantResponseList = new ArrayList<>();
        return ResponseEntity.ok(
                APIResponse.<List<TenantResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Tenants Retrieved successfully")
                        .data(tenantResponseList)
                        .build()
        );
    }
}
