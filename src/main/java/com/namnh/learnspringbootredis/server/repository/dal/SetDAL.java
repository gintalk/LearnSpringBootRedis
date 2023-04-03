package com.namnh.learnspringbootredis.server.repository.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Optional;

public class SetDAL extends AbstractDAL {

    private final SetOperations<String, String> operations;

    @Autowired
    protected SetDAL(StringRedisTemplate stringRedisTemplate) {
        super(stringRedisTemplate);
        this.operations = stringRedisTemplate.opsForSet();
    }

    public long size(@NonNull String key) {
        return Optional.ofNullable(operations.size(key)).orElse(0L);
    }

    public boolean contains(@NonNull String key, @NonNull String value) {
        return contains(key, value, false);
    }

    public boolean contains(@NonNull String key, @NonNull String value, boolean defaultOnError) {
        return Optional.ofNullable(operations.isMember(key, value)).orElse(defaultOnError);
    }

    public Collection<String> getAll(@NonNull String key) {
        return operations.members(key);
    }

    public void add(@NonNull String key, @NonNull String value) {
        operations.add(key, value);
    }

    public void addAll(@NonNull String key, @NonNull Collection<String> values) {
        operations.add(key, values.toArray(new String[]{}));
    }

    public void remove(@NonNull String key, @NonNull String value){
        operations.remove(key, value);
    }
}
