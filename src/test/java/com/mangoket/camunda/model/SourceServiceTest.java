package com.mangoket.camunda.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SourceServiceTest {
    @Test
    void testGetName() {
        assertAll(
                () -> assertEquals("MANGO", SourceService.MANGO.getName()),
                () -> assertEquals("RINGO", SourceService.RINGO.getName())
        );
    }

    @Test
    void testGetValue() {
        assertAll(
                () -> assertEquals("mango", SourceService.MANGO.getValue()),
                () -> assertEquals("ringo", SourceService.RINGO.getValue())
        );
    }

}