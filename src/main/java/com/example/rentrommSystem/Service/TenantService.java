package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.TenantRequest;
import com.example.rentrommSystem.DTO.TenantResponse;
import com.example.rentrommSystem.Mapper.TenantMapper;
import com.example.rentrommSystem.Model.TenantModel;
import com.example.rentrommSystem.Repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    private final String uploadDir = "uploads/id-cards/";

    public List<TenantResponse> getTenantList() {
        return tenantRepository.findAll().stream()
                .map(tenantMapper::toResponse)
                .collect(Collectors.toList());

    }

    public TenantResponse getTenantById(Long id) {
        TenantModel tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found with id: " + id));
        return tenantMapper.toResponse(tenant);
    }

    public TenantResponse createTenant(TenantRequest tenantRequest) {
        TenantModel tenant = tenantMapper.toEntity(tenantRequest);

        if (tenantRequest.getIdCardImage() != null && !tenantRequest.getIdCardImage().isEmpty()) {
            String fileName = saveImage(tenantRequest.getIdCardImage());
            tenant.setIdCardImage(fileName);
        }

        TenantModel saveTenant = tenantRepository.save(tenant);
        return tenantMapper.toResponse(saveTenant);
    }

    public TenantResponse updateTenant(Long id, TenantRequest tenantRequest) {
        TenantModel existingTenant = tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found with id: " + id));

        existingTenant.setTenantName(tenantRequest.getTenantName());
        existingTenant.setStatus(tenantRequest.getStatus());
        existingTenant.setPhoneNumber(tenantRequest.getPhoneNumber());
        existingTenant.setIdCard(tenantRequest.getIdCard());
        existingTenant.setAddress(tenantRequest.getAddress());

        if (tenantRequest.getIdCardImage() != null && !tenantRequest.getIdCardImage().isEmpty()) {
            String fileName = saveImage(tenantRequest.getIdCardImage());
            existingTenant.setIdCardImage(fileName);
        }

        TenantModel updatedTenant = tenantRepository.save(existingTenant);
        return tenantMapper.toResponse(updatedTenant);
    }

    public void deleteTenant(Long id) {
        if (!tenantRepository.existsById(id)) {
            throw new RuntimeException("Tenant not found with id: " + id);
        }
        tenantRepository.deleteById(id);
    }

    private String saveImage(MultipartFile file) {
        try {
            Path root = Paths.get(uploadDir);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), root.resolve(fileName));
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}
