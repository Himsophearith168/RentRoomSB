package com.example.rentrommSystem.Repository;

import com.example.rentrommSystem.Model.TenantModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<TenantModel,Long> {

}
