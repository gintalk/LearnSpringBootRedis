package com.namnh.learnspringbootredis.server.repository;

import com.namnh.learnspringbootredis.server.domain.VendorEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<VendorEntity, String> {
}
