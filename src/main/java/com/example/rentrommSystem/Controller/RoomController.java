package com.example.rentrommSystem.Controller;

import com.example.rentrommSystem.DTO.RoomRequest;
import com.example.rentrommSystem.DTO.RoomResponse;
import com.example.rentrommSystem.Service.RoomService;
import com.example.rentrommSystem.Util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<APIResponse<List<RoomResponse>>> getAllRooms() {
        List<RoomResponse> roomResponseList = roomService.findAll();
        return ResponseEntity.ok(
                APIResponse.<List<RoomResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Room Get Retrived Succesfully")
                        .data(roomResponseList)
                        .build()
        );

    }
    @PostMapping
    public ResponseEntity<APIResponse<RoomResponse>> createRoom(@RequestBody RoomRequest roomRequest) {
        RoomResponse roomResponse = roomService.createRoom(roomRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.<RoomResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("You Create New Room Succeffully")
                        .data(roomResponse)
                        .build()
        );
    }
}
