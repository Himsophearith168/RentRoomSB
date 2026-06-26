package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.utilityRateRequest;
import com.example.rentrommSystem.DTO.utilityRateResponse;
import com.example.rentrommSystem.Mapper.utilityRateMapper;
import com.example.rentrommSystem.Model.utilityRatesModel;
import com.example.rentrommSystem.Model.utilityTypesModel;
import com.example.rentrommSystem.Repository.utilityRateRepository;
import com.example.rentrommSystem.Repository.utilityTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class utilityRateService {

    private final utilityRateRepository utilityRateRepository;
    private final utilityTypeRepository utilityTypeRepository;
    private final utilityRateMapper utilityRateMapper;

    public List<utilityRateResponse> findAll() {
        return utilityRateRepository.findAll().stream()
                .map(utilityRateMapper::toResponse)
                .collect(Collectors.toList());
    }

    public utilityRateResponse findById(Integer id) {
        utilityRatesModel rate = utilityRateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UtilityRate not found with id: " + id));
        return utilityRateMapper.toResponse(rate);
    }

    @Transactional
    public utilityRateResponse createUtilityRate(utilityRateRequest request) {
        utilityTypesModel utilityType = utilityTypeRepository.findById(request.getUtilityTypeId())
                .orElseThrow(() -> new RuntimeException("UtilityType not found with id: " + request.getUtilityTypeId()));

        deactivatePreviousRates(utilityType.getUtilityTypeId());

        utilityRatesModel rate = utilityRateMapper.toEntity(request);
        rate.setUtilityType(utilityType);
        rate.setIsActive(true);

        utilityRatesModel savedRate = utilityRateRepository.save(rate);
        return utilityRateMapper.toResponse(savedRate);
    }

    @Transactional
    public utilityRateResponse updateUtilityRate(Integer id, utilityRateRequest request) {
        utilityRatesModel rate = utilityRateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UtilityRate not found with id: " + id));

        utilityTypesModel utilityType = utilityTypeRepository.findById(request.getUtilityTypeId())
                .orElseThrow(() -> new RuntimeException("UtilityType not found with id: " + request.getUtilityTypeId()));

        if (!rate.getUtilityType().getUtilityTypeId().equals(utilityType.getUtilityTypeId())) {
            deactivatePreviousRates(utilityType.getUtilityTypeId());
        }

        rate.setUtilityType(utilityType);
        rate.setUnitPrice(request.getUnitPrice());
        rate.setEffectiveFrom(request.getEffectiveFrom());

        utilityRatesModel savedRate = utilityRateRepository.save(rate);
        return utilityRateMapper.toResponse(savedRate);
    }

    public void deleteUtilityRate(Integer id) {
        if (!utilityRateRepository.existsById(id)) {
            throw new RuntimeException("UtilityRate not found with id: " + id);
        }
        utilityRateRepository.deleteById(id);
    }

    private void deactivatePreviousRates(Long utilityTypeId) {
        List<utilityRatesModel> activeRates = utilityRateRepository.findByUtilityTypeUtilityTypeId(utilityTypeId);
        for (utilityRatesModel rate : activeRates) {
            if (Boolean.TRUE.equals(rate.getIsActive())) {
                rate.setIsActive(false);
                utilityRateRepository.save(rate);
            }
        }
    }
}
