package com.spring_bandend.spring_bandend.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration // register as a Spring bean
@ConfigurationProperties(prefix = "jwt") // maps jwt.secret, jwt.expiration-ms
@Data // getters / setters for property binding
public class JwtProperties {
    /** HMAC signing key — must match between sign and verify. */
    private String secret;

    /** Access-token lifetime in milliseconds (default 24 hours). */
    private long expirationMs = 86400000L;
}
