package com.example.rentrommSystem.DTO;

import com.example.rentrommSystem.Model.GenderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantRequest {
    @NotNull(message = "name is Require")
    private String tenantName;
    @NotNull(message = "Gender is Require")
    private GenderStatus status;
    @NotNull(message = "PhoneNumber is Require")
    private String phoneNumber;
    @NotNull(message = "ID Card is Require")
    private String idCard;

    private MultipartFile idCardImage;

    private String address;

}
