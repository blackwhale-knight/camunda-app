package com.mangoket.camunda.controller.request.process;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecisionTypeTest {
    @Test
    void testGetStrValue() {
        assertAll(
                () -> assertEquals("approved", DecisionType.APPROVED.getStrValue()),
                () -> assertEquals("rejected", DecisionType.REJECTED.getStrValue())
        );
    }
}