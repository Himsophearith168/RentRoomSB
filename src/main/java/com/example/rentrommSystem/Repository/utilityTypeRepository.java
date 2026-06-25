package com.example.rentrommSystem.Repository;

import com.example.rentrommSystem.Model.utilityTypesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface utilityTypeRepository extends JpaRepository<utilityTypesModel,Long> {
    boolean existsByUtilityTypeName(String utilityTypeName);
}
