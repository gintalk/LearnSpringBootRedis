package com.namnh.learnspringbootredis.server.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorEntity {

    private String id;
    private String name;
    private long createdAt;
    private long modifiedAt;
}
