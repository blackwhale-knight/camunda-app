package com.mangoket.camunda.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SourceService {
    MANGO("MANGO", "mango"),
    RINGO("RINGO", "ringo");

    private final String name;
    private final String value;

    SourceService(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
