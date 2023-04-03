package com.namnh.learnspringbootredis.server.repository.domainspecific;

import com.namnh.learnspringbootredis.server.repository.dal.StringDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public class KeyRepository {

    private final StringDAL stringTemplate;
    private final String keyFormat;

    @Autowired
    KeyRepository(StringDAL stringTemplate) {
        this.stringTemplate = stringTemplate;
        this.keyFormat = "key:%s";
    }

    public long findAndIncrement(@NonNull String keySuffix) {
        return stringTemplate.getAndIncrement(_key(keySuffix));
    }

    private String _key(String keySuffix) {
        return String.format(this.keyFormat, keySuffix);
    }
}
