package com.example.rentrommSystem.Repository;

import com.example.rentrommSystem.Model.utilityRatesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface utilityRateRepository extends JpaRepository<utilityRatesModel, Integer> {
    List<utilityRatesModel> findByUtilityTypeUtilityTypeId(Long utilityTypeId);
    Optional<utilityRatesModel> findFirstByUtilityTypeUtilityTypeIdAndIsActiveTrueOrderByEffectiveFromDesc(Long utilityTypeId);
}
