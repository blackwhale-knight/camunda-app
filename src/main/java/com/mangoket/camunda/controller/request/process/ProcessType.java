package com.mangoket.camunda.controller.request.process;

import lombok.Getter;

@Getter
public enum ProcessType {
    UPDATE_PRODUCT_PRICE("Process_UpdateProductPrice");

    private final String processName;

    ProcessType(String processName) {
        this.processName = processName;
    }
}
