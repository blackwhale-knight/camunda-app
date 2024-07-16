package com.mangoket.camunda.controller.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DecisionTypeTest {
    @Test
    void testGetStrValue() {
        assertAll(
                () -> assertEquals("approved", DecisionType.APPROVED.getValue()),
                () -> assertEquals("rejected", DecisionType.REJECTED.getValue())
        );
    }
}