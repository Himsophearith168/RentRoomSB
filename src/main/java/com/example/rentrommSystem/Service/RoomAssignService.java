package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.RoomAssignRequest;
import com.example.rentrommSystem.DTO.RoomAssignResponse;
import com.example.rentrommSystem.Exception.DuplicateResourceException;
import com.example.rentrommSystem.Mapper.RoomAssignMapper;
import com.example.rentrommSystem.Model.*;
import com.example.rentrommSystem.Repository.RoomAssignRepository;
import com.example.rentrommSystem.Repository.RoomRepository;
import com.example.rentrommSystem.Repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomAssignService {
    private final RoomAssignRepository roomAssignRepository;
    private final RoomRepository roomRepository;
    private final TenantRepository tenantRepository;
    private final RoomAssignMapper roomAssignMapper;

    public List<RoomAssignResponse> getAllAssignments() {
        return roomAssignRepository.findAll().stream()
                .map(roomAssignMapper::toResponse)
                .collect(Collectors.toList());
    }

    public RoomAssignResponse getAssignmentById(Long id) {
        RoomAssignModel assignment = roomAssignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));
        return roomAssignMapper.toResponse(assignment);
    }

    @Transactional
    public RoomAssignResponse assignRoom(RoomAssignRequest request) {
        TenantModel tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() -> new RuntimeException("Tenant not found with id: " + request.getTenantId()));

        RoomModel room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + request.getRoomId()));

        if (room.getStatus() != RoomStatus.AVAILABLE) {
            throw new RuntimeException("Room " + room.getRoomNumber() + " is not available (Status: " + room.getStatus() + ")");
        }

        if (roomAssignRepository.findByTenantIdAndStatus(tenant.getId(), AssignStatus.ACTIVE).isPresent()) {
            throw new DuplicateResourceException("Tenant already has an active room assignment");
        }

        RoomAssignModel assignment = RoomAssignModel.builder()
                .tenant(tenant)
                .room(room)
                .tenantName(tenant.getTenantName())
                .startDate(request.getStartDate() != null ? request.getStartDate().atStartOfDay() : LocalDateTime.now())
                .status(AssignStatus.ACTIVE)
                .build();

        room.setStatus(RoomStatus.OCCUPIED);
        roomRepository.save(room);

        RoomAssignModel savedAssignment = roomAssignRepository.save(assignment);
        return roomAssignMapper.toResponse(savedAssignment);
    }

    @Transactional
    public RoomAssignResponse cancelOrCompleteAssignment(Long id, AssignStatus status) {
        RoomAssignModel assignment = roomAssignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));

        if (assignment.getStatus() != AssignStatus.ACTIVE) {
            throw new RuntimeException("Cannot change status of non-active assignment");
        }

        assignment.setStatus(status);

        RoomModel room = assignment.getRoom();
        if (room != null) {
            room.setStatus(RoomStatus.AVAILABLE);
            roomRepository.save(room);
        }

        RoomAssignModel savedAssignment = roomAssignRepository.save(assignment);
        return roomAssignMapper.toResponse(savedAssignment);
    }
}
