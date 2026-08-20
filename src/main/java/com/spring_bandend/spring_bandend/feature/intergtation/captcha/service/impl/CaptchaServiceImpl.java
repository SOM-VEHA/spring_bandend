package com.spring_bandend.spring_bandend.feature.intergtation.captcha.service.impl;
import com.spring_bandend.spring_bandend.encryption.EncryptionService;
import com.spring_bandend.spring_bandend.feature.intergtation.captcha.component.RandomCodeGenerator;
import com.spring_bandend.spring_bandend.feature.intergtation.captcha.dto.response.CaptchaResponse;
import com.spring_bandend.spring_bandend.feature.intergtation.captcha.service.CaptchaService;
import com.spring_bandend.spring_bandend.feature.intergtation.redis.RedisService;
import com.spring_bandend.spring_bandend.property.CaptchaProperties;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
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
        return CaptchaResponse.builder()
                .captchaId(captchaId)
                .imageBase64(generateCode)
                .enabled(true)
                .build();
    }
    @Override
    public void validate(String captchaId, String captchaData) {
        // When disabled, every validate succeeds without touching Redis
        if (!properties.isEnabled()) {
            return;
        }
        // Load ciphertext, DELETE key immediately, decrypt to plaintext
        //43DRue
        //fdsr235terdyrdtfdsxcfwere
        String storedCode = getStoredCaptchaCode(captchaId);

        if (!storedCode.equals(captchaData.trim())) {
            throw new ValidationException("Incorrect captcha. Please try again");
        }
    }
    private String getStoredCaptchaCode(String captchaId) {
        String key = CAPTCHA_KEY_PREFIX + captchaId;
//fdsfsaerf3w33
        Optional<String> stored = redisService.get(key);

        redisService.remove(key);

        if (stored.isEmpty()){
            throw new ValidationException("Captcha expired. Please try again");
        }
//3gs4
        return encryptionService.decrypt(stored.get());
    }
}
