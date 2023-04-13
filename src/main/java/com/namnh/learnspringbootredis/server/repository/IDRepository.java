package com.namnh.learnspringbootredis.server.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

@Repository
public class IDRepository extends RedisRepository {

    IDRepository() {
        super();
    }

    @Override
    protected String keyspace() {
        return "id";
    }

    public String incrementAndGet(String categoryID) {
        Long id = template.opsForValue().increment(key(categoryID));
        return ObjectUtils.isEmpty(id) ? "1" : id.toString();
    }
}
