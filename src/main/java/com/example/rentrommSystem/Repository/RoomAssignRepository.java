package com.example.rentrommSystem.Repository;

import com.example.rentrommSystem.Model.AssignStatus;
import com.example.rentrommSystem.Model.RoomAssignModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomAssignRepository extends JpaRepository<RoomAssignModel, Long> {
    List<RoomAssignModel> findByRoomId(Long roomId);
    List<RoomAssignModel> findByTenantName(String tenantName);
    List<RoomAssignModel> findByStatus(AssignStatus status);
}
