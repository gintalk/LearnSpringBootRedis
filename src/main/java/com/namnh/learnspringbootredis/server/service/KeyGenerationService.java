package com.namnh.learnspringbootredis.server.service;

import com.namnh.learnspringbootredis.server.repository.domainspecific.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyGenerationService {

    private final KeyRepository keyRepository;

    @Autowired
    KeyGenerationService(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    public long generateKey(KeyCategory keyCategory) {
        return keyRepository.findAndIncrement(keyCategory.getValue());
    }

    public enum KeyCategory {
        TEST("test"), VENDOR("vendor"), GUEST("guest"), MENU("menu"), ORDER("order"), REVIEW("review");

        private final String value;

        KeyCategory(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
