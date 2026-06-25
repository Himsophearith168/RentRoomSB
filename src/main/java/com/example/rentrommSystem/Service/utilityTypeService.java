package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.utilityTypeRequest;
import com.example.rentrommSystem.DTO.utilityTypeResponse;
import com.example.rentrommSystem.Exception.DuplicateResourceException;
import com.example.rentrommSystem.Mapper.utilityTypeMapper;
import com.example.rentrommSystem.Model.utilityTypesModel;
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
//    private final utilityTypesModel utilityTypesModel;

    public List<utilityTypeResponse> findAll(){
        return utilityTypeRepository.findAll().stream()
                .map(utilityTypeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public utilityTypeResponse findById(Long id){
        utilityTypesModel utilityType = utilityTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("UtilityType not found with id: " + id));
        return utilityTypeMapper.toResponse(utilityType);
    }

    public utilityTypeResponse createUtilityType(utilityTypeRequest utilityTypeRequest){
        if (utilityTypeRepository.existsByUtilityTypeName(utilityTypeRequest.getUtilityTypeName())){
            throw new DuplicateResourceException("UtilityType already exists");
        };
        utilityTypesModel utilityType = utilityTypeMapper.toEntity(utilityTypeRequest);
        utilityTypeRepository.save(utilityType);
        return  utilityTypeMapper.toResponse(utilityType);
    }

    public utilityTypeResponse updateUtilityType(Long id,utilityTypeRequest utilityTypeRequest){
        utilityTypesModel utilityType = utilityTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("UtilityType not found with id: " + id));
        if (utilityTypeRepository.existsByUtilityTypeName(utilityTypeRequest.getUtilityTypeName())){
            throw new DuplicateResourceException("UtilityType already exists");
        }
        utilityType.setUtilityTypeName(utilityTypeRequest.getUtilityTypeName());
        utilityType.setBillingTypeStatus(utilityTypeRequest.getBillingTypeStatus());
        utilityType.setUnit(utilityTypeRequest.getUnit());
        return utilityTypeMapper.toResponse(utilityTypeRepository.save(utilityType));
    }

    public void deleteUtilityType(Long id){
        if(!utilityTypeRepository.existsById(id)){
            throw new RuntimeException("UtilityType not found with id: " + id);
        }
        utilityTypeRepository.deleteById(id);
    }



}
