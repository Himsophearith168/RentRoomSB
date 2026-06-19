package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.RoomRequest;
import com.example.rentrommSystem.DTO.RoomResponse;
import com.example.rentrommSystem.Mapper.RoomMapper;
import com.example.rentrommSystem.Model.RoomModel;
import com.example.rentrommSystem.Repository.RoomRepository;
import com.example.rentrommSystem.Exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public List<RoomResponse> findAll() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }

    public RoomResponse findById(Long id) {
        RoomModel findByID = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        return roomMapper.toResponse(findByID);
    }
    public RoomResponse createRoom(RoomRequest roomRequest) {
        if (roomRepository.existsByRoomNumber(roomRequest.getRoomNumber())) {
            throw new DuplicateResourceException("Room number '" + roomRequest.getRoomNumber() + "' already exists!");
        }
        RoomModel room = roomMapper.toEntity(roomRequest);
        RoomModel saveRoom = roomRepository.save(room) ;
        return roomMapper.toResponse(saveRoom);
    }
}
