package com.example.rentrommSystem.Repository;

import com.example.rentrommSystem.Model.RoomModel;
import com.example.rentrommSystem.Model.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long> {
    Optional<RoomModel> findByRoomNumber(String roomNumber);
    List<RoomModel> findByStatus(RoomStatus status);
}
