package com.cognizant.testing.junit;

import com.cognizant.testing.util.EvenChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Advanced JUnit Exercise 1: Parameterized Tests.
 */
class EvenCheckerTest {

    private final EvenChecker evenChecker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100, 0, -4})
    void testIsEven_trueCases(int number) {
        assertTrue(evenChecker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99, -3})
    void testIsEven_falseCases(int number) {
        assertFalse(evenChecker.isEven(number));
    }

    /**
     * Bonus: @CsvSource pairs input with expected result for a single
     * combined parameterized test.
     */
    @ParameterizedTest
    @CsvSource({
            "2, true",
            "3, false",
            "0, true",
            "-10, true",
            "-7, false"
    })
    void testIsEven_withExpectedValue(int number, boolean expected) {
        org.junit.jupiter.api.Assertions.assertEquals(expected, evenChecker.isEven(number));
    }
}
