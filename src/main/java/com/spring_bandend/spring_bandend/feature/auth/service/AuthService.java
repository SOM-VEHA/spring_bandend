package com.spring_bandend.spring_bandend.feature.auth.service;

import com.spring_bandend.spring_bandend.feature.auth.dto.request.LoginRequest;
import com.spring_bandend.spring_bandend.feature.auth.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
}
