package com.namnh.learnspringbootredis.server.repository.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;

import java.util.Map;

public class HashDAL extends AbstractDAL {

    private final HashOperations<String, String, String> operations;

    @Autowired
    protected HashDAL(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
        this.operations = stringRedisTemplate.opsForHash();
    }

    public Map<String, String> get(@NonNull String key) {
        return operations.entries(key);
    }

    public String getValue(@NonNull String key, @NonNull String hashKey) {
        return operations.get(key, hashKey);
    }

    public void add(@NonNull String key, @NonNull String hashKey, @NonNull String hashValue) {
        operations.put(key, hashKey, hashValue);
    }

    public void add(@NonNull String key, @NonNull Map<String, String> hashes) {
        operations.putAll(key, hashes);
    }

    public
}
