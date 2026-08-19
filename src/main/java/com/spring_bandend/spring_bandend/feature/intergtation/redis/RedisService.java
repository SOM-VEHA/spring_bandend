package com.spring_bandend.spring_bandend.feature.intergtation.redis;
import java.time.Duration;
import java.util.Optional;
public interface RedisService {
    void save(String key, String value, Duration ttl);
    void save(String key, String value);
    Optional<String> get(String key);
    boolean remove(String keys);
    boolean exists(String key);
}
