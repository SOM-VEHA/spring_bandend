package com.spring_bandend.spring_bandend.feature.core.user.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String nickName;
    private Boolean enabled;
}
