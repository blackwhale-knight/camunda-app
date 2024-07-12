package com.mangoket.camunda.controller.request.process;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessTypeTest {

    @Test
    void testGetProcessName() {
        assertAll(
                () -> assertEquals("Process_UpdateProductPrice", ProcessType.UPDATE_PRODUCT_PRICE.getProcessName())
        );
    }
}