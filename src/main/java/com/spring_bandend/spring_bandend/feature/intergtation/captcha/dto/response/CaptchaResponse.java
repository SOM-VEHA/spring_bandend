package com.spring_bandend.spring_bandend.feature.intergtation.captcha.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaptchaResponse {
    private String captchaId;
    private String imageBase64;
    private boolean enabled;
}