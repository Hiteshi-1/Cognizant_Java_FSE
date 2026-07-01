package com.cognizant.testing.junit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Advanced JUnit Exercise 3: Test Execution Order.
 *
 * Uses @TestMethodOrder(MethodOrderer.OrderAnnotation.class) with @Order
 * to guarantee tests run in a specific, predictable sequence — useful when
 * tests build on shared state (e.g. step-by-step workflow validation).
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedTests {

    // Shared list to demonstrate that execution order is honored
    private static final List<String> executionLog = new ArrayList<>();

    @Test
    @Order(1)
    void firstTest() {
        executionLog.add("first");
        assertEquals(1, executionLog.size());
    }

    @Test
    @Order(2)
    void secondTest() {
        executionLog.add("second");
        assertEquals(2, executionLog.size());
        assertEquals("first", executionLog.get(0));
    }

    @Test
    @Order(3)
    void thirdTest() {
        executionLog.add("third");
        assertEquals(3, executionLog.size());
        assertEquals(List.of("first", "second", "third"), executionLog);
    }
}
