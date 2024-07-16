package com.mangoket.camunda.controller.request;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum DecisionType {
    APPROVED("approved"),
    REJECTED("rejected");

    private final String value;

    DecisionType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
