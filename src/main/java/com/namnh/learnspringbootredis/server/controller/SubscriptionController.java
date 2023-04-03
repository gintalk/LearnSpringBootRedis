package com.namnh.learnspringbootredis.server.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    @PutMapping("/subscriptions")
    void subscribe(@RequestParam String userID, @RequestParam String vendorID){

    }
}
