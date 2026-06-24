package com.example.rentrommSystem.Controller;

import com.example.rentrommSystem.DTO.utilityTypeResponse;
import com.example.rentrommSystem.Mapper.utilityTypeMapper;
import com.example.rentrommSystem.Service.utilityTypeService;
import com.example.rentrommSystem.Util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/utilityTypes")
public class utilityTypesController {
    private final utilityTypeService utilityTypeService;
    private final utilityTypeMapper utilityTypeMapper;

    @GetMapping
    public ResponseEntity<APIResponse<List<utilityTypeResponse>>> getAllUtilityTypes(){
        List<utilityTypeResponse> utilityTypeResponse = utilityTypeService.findAll();
        return ResponseEntity.ok(
                APIResponse.<List<utilityTypeResponse>>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Get All UtilityType Succesffully")
                        .data(utilityTypeResponse)
                        .build()

        );
    }
}
