package com.example.rentrommSystem.Repository;

import com.example.rentrommSystem.Model.BillModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillModel, Long> {
    List<BillModel> findByRoomAssignmentId(Long assignmentId);
}
