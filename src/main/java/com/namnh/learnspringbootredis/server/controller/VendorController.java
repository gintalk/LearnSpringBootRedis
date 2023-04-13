package com.namnh.learnspringbootredis.server.controller;

import com.namnh.learnspringbootredis.server.domain.VendorEntity;
import com.namnh.learnspringbootredis.server.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VendorController {

    private final VendorService vendorService;
    private final int defaultPageSize;

    @Autowired
    VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
        this.defaultPageSize = 20;
    }

    @PostMapping("/vendors")
    void createVendor(@RequestBody VendorEntity vendor) {
        vendorService.create(vendor);
    }

    @GetMapping("/vendors")
    ResponseEntity<PagedBody<VendorEntity>> getVendorPage(@RequestParam(name = "page-number") int pageNumber, @RequestParam(name = "page-size") int pageSize) {
        if(pageSize <= 0 || pageSize > defaultPageSize){
            pageSize = defaultPageSize;
        }

        Page<VendorEntity> page = vendorService.getPage(pageNumber, pageSize);

        return transformPage(page);
    }

    private <T> ResponseEntity<PagedBody<T>> transformPage(Page<T> page) {
        return ResponseEntity.ok().body(PagedBody.of(page));
    }
}