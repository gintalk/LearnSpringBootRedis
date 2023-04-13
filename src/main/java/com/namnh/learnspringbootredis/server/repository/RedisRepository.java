package com.namnh.learnspringbootredis.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public abstract class RedisRepository {

    private final String keyspace;
    @Autowired
    protected StringRedisTemplate template;

    protected RedisRepository() {
        this.keyspace = keyspace();
    }

    protected final String key(String... keySuffixes) {
        StringBuilder sb = new StringBuilder(keyspace);
        for (String keySuffix : keySuffixes) {
            sb.append(":").append(keySuffix);
        }
        return sb.toString();
    }

    protected abstract String keyspace();
}
