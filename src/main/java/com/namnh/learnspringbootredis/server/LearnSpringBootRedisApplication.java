package com.namnh.learnspringbootredis.server;

import com.namnh.learnspringbootredis.server.domain.VendorEntity;
import com.namnh.learnspringbootredis.server.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class LearnSpringBootRedisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LearnSpringBootRedisApplication.class, args);
    }

    @Bean
    @Autowired
    CommandLineRunner preloadVendorDatabase(VendorService vendorService) {
        return args -> {
            for (int i = 0; i < 100; i++) {
                VendorEntity vendor = new VendorEntity();
                vendor.setName(UUID.randomUUID().toString());
                vendorService.create(vendor);
            }
        };
    }
}
