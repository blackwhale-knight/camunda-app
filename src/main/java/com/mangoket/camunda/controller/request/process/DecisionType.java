package com.mangoket.camunda.controller.request.process;

import lombok.Getter;

@Getter
public enum DecisionType {
    APPROVED("approved"),
    REJECTED("rejected");

    private final String strValue;

    DecisionType(String strValue) {
        this.strValue = strValue;
    }
}
