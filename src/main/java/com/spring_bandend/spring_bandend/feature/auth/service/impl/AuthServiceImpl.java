package com.spring_bandend.spring_bandend.feature.auth.service.impl;

import com.spring_bandend.spring_bandend.entity.User;
import com.spring_bandend.spring_bandend.feature.auth.dto.request.LoginRequest;
import com.spring_bandend.spring_bandend.feature.auth.dto.response.AuthResponse;
import com.spring_bandend.spring_bandend.feature.auth.service.AuthService;
import com.spring_bandend.spring_bandend.feature.auth.service.TokenService;
import com.spring_bandend.spring_bandend.feature.auth.validator.UserValidator;
import com.spring_bandend.spring_bandend.feature.core.user.dto.response.UserResponse;
import com.spring_bandend.spring_bandend.mapper.UserMapper;
import com.spring_bandend.spring_bandend.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserValidator userValidator;
    private final TokenService tokenService;
    private final UserMapper userMapper;
    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userValidator.validateLoginCredentials(request.getUsername(), request.getPassword());
        return tokenService.issue(userMapper.toResponse(user));
    }
}
