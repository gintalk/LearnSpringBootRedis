package com.namnh.learnspringbootredis.server.repository;

import com.namnh.learnspringbootredis.server.domain.VendorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Repository
public class VendorRepository extends RedisHashRepository<VendorEntity> {

    private final String primaryIndexKey;

    VendorRepository() {
        super(VendorEntity.class);
        this.primaryIndexKey = "index:_primary:vendor";
    }

    @Override
    protected String keyspace() {
        return "vendor";
    }

    public VendorEntity find(String vendorID) {
        return deserialize(template.opsForHash().entries(key(vendorID)));
    }

    public Page<VendorEntity> findPage(int offset, int count) {
        List<VendorEntity> vendors = new ArrayList<>();

        // Redis zrange is inclusive
        Set<String> vendorIDs = template.opsForZSet().range(primaryIndexKey, offset, offset + count - 1);
        if (ObjectUtils.isEmpty(vendorIDs)) {
            vendorIDs = Collections.emptySet();
        }
        for (String vendorID : vendorIDs) {
            String key = key(vendorID);
            vendors.add(deserialize(template.opsForHash().entries(key)));
        }

        Long size = template.opsForZSet().size(primaryIndexKey);

        return new PageImpl<>(vendors, PageRequest.of(offset, count), ObjectUtils.isEmpty(size) ? 0L : size);
    }

    public void save(String vendorID, VendorEntity vendor, long score) {
        String key = key(vendorID);
        Map<String, String> hashes = serialize(vendor);

        template.opsForHash().putAll(key, hashes);
        template.opsForZSet().add(primaryIndexKey, vendorID, score);
    }
}
