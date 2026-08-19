package com.spring_bandend.spring_bandend.feature.auth.dto.response;
import com.spring_bandend.spring_bandend.feature.core.user.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String type;
    private UserResponse user;
    public static AuthResponse of(String token, UserResponse user){
        return AuthResponse.builder()
                .token(token)
                .type("Bearer")
                .user(user)
                .build();
    }
}

