package com.namnh.learnspringbootredis.server.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ResponseBody {

    @JsonProperty(index = 0)
    private long timestamp;
    @JsonProperty(index = 1)
    private String message;
    @JsonProperty(index = 2)
    private Object payload;
}
