package com.example.rentrommSystem.Repository;

import com.example.rentrommSystem.Model.AssignStatus;
import com.example.rentrommSystem.Model.RoomAssignModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomAssignRepository extends JpaRepository<RoomAssignModel, Long> {
    List<RoomAssignModel> findByRoomId(Long roomId);
    List<RoomAssignModel> findByTenantName(String tenantName);
    List<RoomAssignModel> findByStatus(AssignStatus status);
    Optional<RoomAssignModel> findByTenantIdAndStatus(Long tenantId, AssignStatus status);
    Optional<RoomAssignModel> findByRoomIdAndStatus(Long roomId, AssignStatus status);
}
