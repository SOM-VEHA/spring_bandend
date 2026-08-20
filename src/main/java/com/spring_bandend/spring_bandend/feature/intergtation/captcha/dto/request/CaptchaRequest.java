package com.spring_bandend.spring_bandend.feature.intergtation.captcha.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CaptchaRequest {
    @NotBlank
    private String captchaId;
    @NotBlank
    private String captchaData;
}