package com.mangoket.camunda.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Variable {
    private String name;
    private String value;

    public Variable(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
