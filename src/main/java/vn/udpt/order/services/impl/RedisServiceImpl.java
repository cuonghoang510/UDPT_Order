package vn.udpt.order.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import vn.udpt.order.services.RedisService;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public void addObject(String key, String value, Long secondExpiredTime) {
        long start = System.currentTimeMillis();
        this.redisTemplate.opsForValue().set(key, value, secondExpiredTime, TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        log.debug("Redis - Add Data in {} ms: key {} ", end - start, key);
    }

    @SneakyThrows
    public Optional<Object> getObject(String key) {
        try {
            long start = System.currentTimeMillis();
            Object value = this.redisTemplate.opsForValue().get(key);
            long end = System.currentTimeMillis();
            log.debug("Redis - Get Data in {} ms : key {}", end - start, key);
            return Optional.ofNullable(value);
        } catch (Throwable var7) {
            Throwable $ex = var7;
            throw $ex;
        }
    }

    @SneakyThrows
    public <T> T getObject(String key, Class<T> destinationType) {
        try {
            return this.getObject(key).map((o) -> {
                try {
                    return this.objectMapper.readValue(o.toString(), destinationType);
                } catch (JsonProcessingException var4) {
                    JsonProcessingException e = var4;
                    throw new RuntimeException(e);
                }
            }).orElse((T) null);
        } catch (Throwable var4) {
            Throwable $ex = var4;
            throw $ex;
        }
    }

    public void removeObject(String key) {
        long start = System.currentTimeMillis();
        this.redisTemplate.delete(key);
        long end = System.currentTimeMillis();
        log.debug("Redis - Remove Object in {} ms : key {}", end - start, key);
    }

    public RedisServiceImpl(final RedisTemplate<String, Object> redisTemplate, final ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }
}
