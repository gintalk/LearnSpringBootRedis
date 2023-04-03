package com.namnh.learnspringbootredis.server.repository.dal;

import org.springframework.data.redis.core.StringRedisTemplate;

public abstract class AbstractDAL {

    protected final StringRedisTemplate stringRedisTemplate;

    protected AbstractDAL(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
}
