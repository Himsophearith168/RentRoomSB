package com.example.rentrommSystem.Controller;

import com.example.rentrommSystem.DTO.utilityTypeRequest;
import com.example.rentrommSystem.DTO.utilityTypeResponse;
import com.example.rentrommSystem.Mapper.utilityTypeMapper;
import com.example.rentrommSystem.Service.utilityTypeService;
import com.example.rentrommSystem.Util.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("{id}")
    public ResponseEntity<APIResponse<utilityTypeResponse>> getUtilityType(@PathVariable Long id){
        utilityTypeResponse utilityTypeResponse = utilityTypeService.findById(id);
        return ResponseEntity.ok(
                APIResponse.<utilityTypeResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("You Retrived data Successfuully")
                        .data(utilityTypeResponse)
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<APIResponse<utilityTypeResponse>> saveUtilityType(@RequestBody utilityTypeRequest utilityTypeRequest){
        utilityTypeResponse utilityTypeResponse = utilityTypeService.createUtilityType(utilityTypeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                APIResponse.<utilityTypeResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Create New UtilityType Successfully")
                .data(utilityTypeResponse)
                        .build()
        );
    }
    @PutMapping("{id}")
    public ResponseEntity<APIResponse<utilityTypeResponse>> updateUtilityType(@PathVariable Long id, @RequestBody utilityTypeRequest utilityTypeRequest){
        utilityTypeResponse utilityTypeResponse = utilityTypeService.updateUtilityType(id, utilityTypeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                APIResponse.<utilityTypeResponse>builder()
                        .status(HttpStatus.OK.value())
                        .message("Update Successfully")
                        .data(utilityTypeResponse)
                .build()
        );
    }
    @DeleteMapping("{id}")
    public ResponseEntity<APIResponse<Void>> deleteUtilityType(@PathVariable Long id){
        utilityTypeService.deleteUtilityType(id);
        return ResponseEntity.ok(
                APIResponse.<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Delete Successfuuly")
                        .build()
        );
    }

}
