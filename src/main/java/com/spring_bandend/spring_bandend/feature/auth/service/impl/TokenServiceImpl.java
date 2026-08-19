package com.spring_bandend.spring_bandend.feature.auth.service.impl;

import com.spring_bandend.spring_bandend.feature.auth.dto.request.LoginRequest;
import com.spring_bandend.spring_bandend.feature.auth.dto.response.AuthResponse;
import com.spring_bandend.spring_bandend.feature.auth.service.AuthService;
import com.spring_bandend.spring_bandend.feature.auth.service.TokenService;
import com.spring_bandend.spring_bandend.feature.core.user.dto.response.UserResponse;
import com.spring_bandend.spring_bandend.feature.intergtation.redis.RedisService;
import com.spring_bandend.spring_bandend.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final JwtService jwtService;
    private final RedisService redisService;
    public static final String TOKEN_KEY_PREFIX = "token:";
    @Override
    public AuthResponse issue(UserResponse user) {
        String token = jwtService.generateToken(user.getUsername());
        String username = user.getUsername();
        redisService.save(TOKEN_KEY_PREFIX + username, token, jwtService.getExpirationDuration());
        return AuthResponse.of(token,user);
    }
}
