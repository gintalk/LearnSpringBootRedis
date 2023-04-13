package com.namnh.learnspringbootredis.server.service;

import com.namnh.learnspringbootredis.server.repository.IDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IDGenerationService {

    private final IDRepository idRepository;

    @Autowired
    IDGenerationService(IDRepository idRepository) {
        this.idRepository = idRepository;
    }

    public String get(IDCategory idCategory) {
        return idRepository.incrementAndGet(idCategory.getValue());
    }
}
