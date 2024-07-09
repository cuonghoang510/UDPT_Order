package vn.udpt.order.services;

import java.util.Optional;

public interface RedisService {
    void addObject(String key, String data, Long secondExpiredTime);

    Optional<Object> getObject(String key);

    <T> T getObject(String key, Class<T> destinationType);

    void removeObject(String key);
}
