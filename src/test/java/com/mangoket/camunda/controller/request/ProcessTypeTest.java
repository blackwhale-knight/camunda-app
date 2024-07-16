package com.mangoket.camunda.controller.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProcessTypeTest {

    @Test
    void testGetProcessName() {
        assertAll(
                () -> assertEquals("Process_UpdateProductPrice", ProcessType.UPDATE_PRODUCT_PRICE.getProcessName())
        );
    }
}