package com.example.rentrommSystem.Service;

import com.example.rentrommSystem.DTO.RoomResponse;
import com.example.rentrommSystem.Mapper.RoomMapper;
import com.example.rentrommSystem.Repository.RoomRepository;
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
}
