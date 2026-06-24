package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.utilityTypeResponse;
import com.example.rentrommSystem.Mapper.utilityTypeMapper;
import com.example.rentrommSystem.Repository.TenantRepository;
import com.example.rentrommSystem.Repository.utilityTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class utilityTypeService {

    private final utilityTypeRepository utilityTypeRepository;
    private final TenantRepository tenantRepository;
    private final utilityTypeMapper utilityTypeMapper;

    public List<utilityTypeResponse> findAll(){
        return utilityTypeRepository.findAll().stream()
                .map(utilityTypeMapper::toResponse)
                .collect(Collectors.toList());
    }

}
