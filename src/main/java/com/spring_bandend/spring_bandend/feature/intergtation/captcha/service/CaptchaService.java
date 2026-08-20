package com.spring_bandend.spring_bandend.feature.intergtation.captcha.service;
import com.spring_bandend.spring_bandend.feature.intergtation.captcha.dto.response.CaptchaResponse;
public interface CaptchaService {
    CaptchaResponse generate();
    //34Sgee
    void validate(String captchaId, String captchaData);
}
