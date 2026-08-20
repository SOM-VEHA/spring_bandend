package com.spring_bandend.spring_bandend.feature.intergtation.captcha.controller;

import com.spring_bandend.spring_bandend.base.BaseSuccess;
import com.spring_bandend.spring_bandend.feature.core.role.dto.response.RoleResponse;
import com.spring_bandend.spring_bandend.feature.intergtation.captcha.dto.request.CaptchaRequest;
import com.spring_bandend.spring_bandend.feature.intergtation.captcha.service.CaptchaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/captcha")
@RequiredArgsConstructor
public class CaptchaController {
    private final CaptchaService captchaService;
    @GetMapping // GET /api/captcha
    public ResponseEntity<?> generate() {
        // Delegate to service, wrap payload in the standard API envelope
        return ResponseEntity.ok(captchaService.generate());
    }
    @PostMapping("/validate") // POST /api/captcha/validate
    public ResponseEntity<?> validate(@Valid // run Bean Validation on the request body
            @RequestBody CaptchaRequest request) {
        captchaService.validate(request.getCaptchaId(), request.getCaptchaData());
        return ResponseEntity.ok(
                BaseSuccess.<RoleResponse>builder()
                        .status(true)
                        .code(HttpStatus.OK.value())
                        .message("Success")
                        .timestamp(LocalDateTime.now())
                        .data(null)
                        .build()
        );
    }
}
