package com.cognizant.testing.util;

/**
 * PerformanceTester – Advanced JUnit Exercise 5: Timeout and Performance Testing.
 */
public class PerformanceTester {

    /**
     * Simulates a task that completes quickly (used to test timeout passes).
     */
    public void performTask() throws InterruptedException {
        Thread.sleep(50); // well within any reasonable timeout
    }

    /**
     * Simulates a task that takes too long (used to demonstrate a timeout failure).
     */
    public void performSlowTask() throws InterruptedException {
        Thread.sleep(2000);
    }
}
