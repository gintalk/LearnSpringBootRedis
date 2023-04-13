package com.namnh.learnspringbootredis.server.service;

import com.namnh.learnspringbootredis.server.domain.VendorEntity;
import com.namnh.learnspringbootredis.server.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final IDGenerationService idGenerationService;


    @Autowired
    VendorService(IDGenerationService idGenerationService, VendorRepository vendorRepository) {
        this.idGenerationService = idGenerationService;
        this.vendorRepository = vendorRepository;
    }

    public VendorEntity get(String vendorID) {
        return vendorRepository.find(vendorID);
    }

    public Page<VendorEntity> getPage(int page, int count) {
        int offset = page * count;
        return vendorRepository.findPage(offset, count);
    }

    public void create(VendorEntity vendor) {
        vendor.setId(idGenerationService.get(IDCategory.VENDOR));
        vendor.setCreatedAt(System.currentTimeMillis());
        vendor.setModifiedAt(vendor.getCreatedAt());

        vendorRepository.save(vendor.getId(), vendor, vendor.getCreatedAt());
    }
}
