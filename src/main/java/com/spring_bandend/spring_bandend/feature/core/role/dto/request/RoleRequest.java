package com.spring_bandend.spring_bandend.feature.core.role.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequest {
    @NotBlank(message = "Name Can not Bland")
    @Size(max = 99,min = 5)
    private String name;
    @Size(max=500)
    private String description;
}
