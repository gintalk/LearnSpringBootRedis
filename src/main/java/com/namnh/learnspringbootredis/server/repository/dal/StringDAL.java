package com.namnh.learnspringbootredis.server.repository.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class StringDAL extends AbstractDAL {

    private final ValueOperations<String, String> operations;

    @Autowired
    protected StringDAL(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
        this.operations = this.stringRedisTemplate.opsForValue();
    }

    public Optional<String> get(@NonNull String key) {
        return Optional.ofNullable(operations.get(key));
    }

    public void set(@NonNull String key, @NonNull String value) {
        operations.set(key, value);
    }

    public long getAndIncrement(@NonNull String key) {
        Long ret = operations.increment(key);
        return ret == null ? 0 : ret;
    }

    public void mset(@NonNull Map<String, String> keyValues) {
        operations.multiSet(keyValues);
    }
}
