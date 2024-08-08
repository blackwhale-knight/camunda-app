package com.mangoket.camunda.controller.request;

import lombok.Getter;

@Getter
public enum ProcessType {
    UPDATE_PRODUCT_PRICE("Process_UpdateProductPrice"),
    TEST("Process_Test");

    private final String processName;

    ProcessType(String processName) {
        this.processName = processName;
    }
}
