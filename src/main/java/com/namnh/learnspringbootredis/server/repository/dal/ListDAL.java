package com.namnh.learnspringbootredis.server.repository.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class ListDAL extends AbstractDAL {

    private final ListOperations<String, String> operations;

    @Autowired
    protected ListDAL(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
        this.operations = this.stringRedisTemplate.opsForList();
    }

    public long size(@NonNull String key) {
        return Optional.ofNullable(operations.size(key)).orElse(0L);
    }

    public boolean contains(@NonNull String key, @NonNull String value) {
        return Optional.ofNullable(operations.indexOf(key, value)).orElse(-1L) >= 0;
    }

    public Collection<String> getRange(@NonNull String key, long start, long end) {
        return operations.range(key, start, end);
    }

    public Optional<String> getAt(@NonNull String key, long index) {
        return Optional.ofNullable(operations.index(key, index));
    }

    public void setAt(@NonNull String key, @NonNull String value, long index) {
        operations.set(key, index, value);
    }

    public void addAtFront(@NonNull String key, @NonNull String value) {
        operations.leftPush(key, value)
    }

    public void addAllAtFront(@NonNull String key, @NonNull Collection<String> values) {
        operations.leftPushAll(key, values);
    }

    public void addAtEnd(@NonNull String key, @NonNull String value) {
        operations.rightPush(key, value);
    }

    public void addAllAtEnd(@NonNull String key, @NonNull Collection<String> values) {
        operations.rightPushAll(key, values);
    }

    public void remove(@NonNull String key, @NonNull String value) {
        operations.remove(key, 1, value);
    }

    public void removeMany(@NonNull String key, @NonNull String value, long count) {
        operations.remove(key, count, count);
    }
}
