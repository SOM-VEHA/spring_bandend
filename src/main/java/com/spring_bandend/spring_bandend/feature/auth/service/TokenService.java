package com.spring_bandend.spring_bandend.feature.auth.service;

import com.spring_bandend.spring_bandend.feature.auth.dto.response.AuthResponse;
import com.spring_bandend.spring_bandend.feature.core.user.dto.response.UserResponse;

public interface TokenService {
    AuthResponse issue(UserResponse user);
}
