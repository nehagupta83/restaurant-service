package com.food.delivery.restaurantservice.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    OPEN("OPEN"),
    CLOSE("CLOSE");

    private final String value;
    Status(String value) {
        this.value = value;
    }

    @JsonValue
    public String getVal() {
        return value;
    }
}
