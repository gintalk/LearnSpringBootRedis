package com.namnh.learnspringbootredis.server.service;

public enum IDCategory {
    TEST("test"), VENDOR("vendor"), GUEST("guest"), MENU("menu"), ORDER("order"), REVIEW("review");

    private final String value;

    IDCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
