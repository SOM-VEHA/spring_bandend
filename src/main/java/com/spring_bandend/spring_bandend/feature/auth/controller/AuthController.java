package com.spring_bandend.spring_bandend.feature.auth.controller;

import com.spring_bandend.spring_bandend.base.BaseSuccess;
import com.spring_bandend.spring_bandend.feature.auth.dto.request.LoginRequest;
import com.spring_bandend.spring_bandend.feature.auth.dto.response.AuthResponse;
import com.spring_bandend.spring_bandend.feature.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse login = authService.login(request);
        return ResponseEntity.ok(
                BaseSuccess.builder()
                        .status(true)
                        .code(HttpStatus.OK.value())
                        .message("Success")
                        .timestamp(LocalDateTime.now())
                        .data(login)
                        .build()

        );
    }
}
