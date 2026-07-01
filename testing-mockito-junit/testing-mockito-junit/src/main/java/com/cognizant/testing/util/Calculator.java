package com.cognizant.testing.util;

/**
 * Calculator – simple Java class used in JUnit Exercise 2 (Writing Basic JUnit Tests)
 * and Exercise 4 (Arrange-Act-Assert pattern with setup/teardown).
 */
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
}
