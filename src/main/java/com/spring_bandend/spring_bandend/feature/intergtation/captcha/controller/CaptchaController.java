package com.spring_bandend.spring_bandend.feature.intergtation.captcha.controller;

import com.spring_bandend.spring_bandend.feature.intergtation.captcha.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
