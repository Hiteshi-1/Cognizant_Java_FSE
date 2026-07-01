package com.cognizant.testing.junit;

import com.cognizant.testing.util.PerformanceTester;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

/**
 * Advanced JUnit Exercise 5: Timeout and Performance Testing.
 */
class PerformanceTesterTest {

    private final PerformanceTester performanceTester = new PerformanceTester();

    /** @Timeout annotation form — fails if performTask() exceeds 1 second. */
    @Test
    @Timeout(1)
    void testPerformTask_completesWithinTimeout() throws InterruptedException {
        performanceTester.performTask();
    }

    /** assertTimeout() form — equivalent check, different style. */
    @Test
    void testPerformTask_assertTimeoutStyle() {
        assertTimeout(Duration.ofSeconds(1), () -> performanceTester.performTask());
    }

    /**
     * Demonstrates a timeout FAILING: performSlowTask() takes 2s but the
     * timeout is 500ms, so assertTimeout() itself throws an AssertionError.
     */
    @Test
    void testPerformSlowTask_exceedsTimeout() {
        assertThrows(AssertionError.class, () ->
                assertTimeout(Duration.ofMillis(500), () -> performanceTester.performSlowTask())
        );
    }
}
