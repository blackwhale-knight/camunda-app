package com.mangoket.camunda.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SourceService {
    MANGO("MANGO", "mango"),
    RINGO("RINGO", "ringo");

    private final String name;
    @Getter
    private final String value;

    SourceService(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
