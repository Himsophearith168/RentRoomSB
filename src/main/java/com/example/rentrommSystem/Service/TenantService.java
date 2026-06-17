package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.TenantResponse;
import com.example.rentrommSystem.Mapper.TenantMapper;
import com.example.rentrommSystem.Repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    public List<TenantResponse> getTenantList() {
        return tenantRepository.findAll().stream()
                .map(tenantMapper::toResponse)
                .collect(Collectors.toList());

    }
}
