package com.spring_bandend.spring_bandend.feature.intergtation.captcha.service.impl;
import com.spring_bandend.spring_bandend.encryption.EncryptionService;
import com.spring_bandend.spring_bandend.feature.intergtation.captcha.component.RandomCodeGenerator;
import com.spring_bandend.spring_bandend.feature.intergtation.captcha.dto.response.CaptchaResponse;
import com.spring_bandend.spring_bandend.feature.intergtation.captcha.service.CaptchaService;
import com.spring_bandend.spring_bandend.feature.intergtation.redis.RedisService;
import com.spring_bandend.spring_bandend.property.CaptchaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CaptchaServiceImpl implements CaptchaService {
    private final static String CAPTCHA_KEY_PREFIX = "captcha:";
    private final RedisService redisService;
    private final CaptchaProperties properties;
    private final RandomCodeGenerator randomCodeGenerator;
    private final EncryptionService encryptionService;
    @Override
    public CaptchaResponse generate() {
        // Feature flag — client should skip captcha fields when false
        if (!properties.isEnabled()) {
//            log.debug("captcha generate skipped — disabled");
            return CaptchaResponse.builder().enabled(false).build(); // no captchaId / imageBase64
        }
        String captchaId = UUID.randomUUID().toString();
        String generateCode = randomCodeGenerator.generate(properties.getLength());
        System.out.println("Captcha Code");
        System.out.println(generateCode);
        redisService.save(CAPTCHA_KEY_PREFIX + captchaId, encryptionService.encrypt(generateCode), Duration.ofMinutes(properties.getTtlMinutes()));
        return CaptchaResponse.builder().captchaId(captchaId).imageBase64(generateCode).build();
    }
    @Override
    public void validate(String captchaId, String captchaData) {
    }
}
