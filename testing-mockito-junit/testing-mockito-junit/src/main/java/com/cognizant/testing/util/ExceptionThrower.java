package com.cognizant.testing.util;

/**
 * ExceptionThrower – Advanced JUnit Exercise 4: Exception Testing.
 */
public class ExceptionThrower {

    public void throwException() {
        throw new IllegalStateException("Something went wrong");
    }

    public void throwExceptionIfNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must not be negative: " + value);
        }
    }
}
