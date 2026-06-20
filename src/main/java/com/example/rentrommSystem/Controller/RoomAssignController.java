package com.example.rentrommSystem.Controller;

import com.example.rentrommSystem.DTO.RoomAssignRequest;
import com.example.rentrommSystem.DTO.RoomAssignResponse;
import com.example.rentrommSystem.Model.AssignStatus;
import com.example.rentrommSystem.Service.RoomAssignService;
import com.example.rentrommSystem.Util.APIResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
@RequiredArgsConstructor
public class RoomAssignController {
    private final RoomAssignService roomAssignService;

    @GetMapping
    public ResponseEntity<APIResponse<List<RoomAssignResponse>>> getAllAssignments() {
        List<RoomAssignResponse> list = roomAssignService.getAllAssignments();
        return ResponseEntity.ok(
                APIResponse.<List<RoomAssignResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("Room assignments retrieved successfully")
                        .data(list)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<RoomAssignResponse>> getAssignmentById(@PathVariable Long id) {
        RoomAssignResponse response = roomAssignService.getAssignmentById(id);
        return ResponseEntity.ok(
                APIResponse.<RoomAssignResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Room assignment retrieved successfully")
                        .data(response)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<APIResponse<RoomAssignResponse>> assignRoom(@RequestBody @Valid RoomAssignRequest request) {
        RoomAssignResponse response = roomAssignService.assignRoom(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.<RoomAssignResponse>builder()
                        .status(HttpStatus.CREATED.value())
                        .message("Room assigned successfully")
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<APIResponse<RoomAssignResponse>> completeAssignment(@PathVariable Long id) {
        RoomAssignResponse response = roomAssignService.cancelOrCompleteAssignment(id, AssignStatus.COMPLETED);
        return ResponseEntity.ok(
                APIResponse.<RoomAssignResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Room assignment completed successfully")
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<APIResponse<RoomAssignResponse>> cancelAssignment(@PathVariable Long id) {
        RoomAssignResponse response = roomAssignService.cancelOrCompleteAssignment(id, AssignStatus.CANCELLED);
        return ResponseEntity.ok(
                APIResponse.<RoomAssignResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Room assignment cancelled successfully")
                        .data(response)
                        .build()
        );
    }
}
