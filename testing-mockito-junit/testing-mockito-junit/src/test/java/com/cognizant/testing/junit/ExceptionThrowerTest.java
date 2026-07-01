package com.cognizant.testing.junit;

import com.cognizant.testing.util.ExceptionThrower;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Advanced JUnit Exercise 4: Exception Testing.
 */
class ExceptionThrowerTest {

    private final ExceptionThrower exceptionThrower = new ExceptionThrower();

    @Test
    void testThrowException() {
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                exceptionThrower::throwException
        );
        assertEquals("Something went wrong", ex.getMessage());
    }

    @Test
    void testThrowExceptionIfNegative_withNegativeValue() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> exceptionThrower.throwExceptionIfNegative(-5)
        );
        assertEquals("Value must not be negative: -5", ex.getMessage());
    }

    @Test
    void testThrowExceptionIfNegative_withPositiveValue_doesNotThrow() {
        // No exception expected; calling the method directly is sufficient.
        exceptionThrower.throwExceptionIfNegative(5);
    }
}
