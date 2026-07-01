package com.cognizant.testing.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Exercise 3: Assertions in JUnit.
 */
class AssertionsTest {

    @Test
    void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);

        // Assert true
        assertTrue(5 > 3);

        // Assert false
        assertFalse(5 < 3);

        // Assert null
        assertNull(null);

        // Assert not null
        assertNotNull(new Object());

        // A few extras for completeness
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        assertNotEquals(1, 2);
        assertThrows(ArithmeticException.class, () -> { int x = 1 / 0; });
    }
}
