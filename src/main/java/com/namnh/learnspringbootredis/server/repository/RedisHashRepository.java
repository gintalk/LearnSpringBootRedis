package com.namnh.learnspringbootredis.server.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

public abstract class RedisHashRepository<T> extends RedisRepository {

    private final Class<T> domainObjectClass;
    @Autowired
    protected StringRedisTemplate template;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    protected RedisHashRepository(Class<T> domainObjectClass) {
        super();
        this.domainObjectClass = domainObjectClass;
    }

    protected Map<String, String> serialize(T t) {
        return objectMapper.convertValue(t, new TypeReference<>() {
        });
    }

    protected T deserialize(Map<Object, Object> hashes) {
        return objectMapper.convertValue(hashes, domainObjectClass);
    }
}
