package com.cognizant.testing.junit;

import com.cognizant.testing.util.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Exercise 2: Writing Basic JUnit Tests.
 * JUnit Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures,
 *                   Setup and Teardown Methods.
 *
 * Note: JUnit 5 uses @BeforeEach/@AfterEach instead of the JUnit 4
 * @Before/@After annotations mentioned in the original hands-on text.
 */
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        // Arrange (shared fixture): a fresh Calculator before every test
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Test
    void testAdd() {
        // Arrange
        int a = 2, b = 3;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(5, result);
    }

    @Test
    void testSubtract() {
        // Arrange
        int a = 10, b = 4;

        // Act
        int result = calculator.subtract(a, b);

        // Assert
        assertEquals(6, result);
    }

    @Test
    void testMultiply() {
        // Arrange
        int a = 6, b = 7;

        // Act
        int result = calculator.multiply(a, b);

        // Assert
        assertEquals(42, result);
    }

    @Test
    void testDivide() {
        // Arrange
        int a = 20, b = 4;

        // Act
        int result = calculator.divide(a, b);

        // Assert
        assertEquals(5, result);
    }

    @Test
    void testDivideByZeroThrows() {
        // Arrange
        int a = 10, b = 0;

        // Act + Assert
        assertThrows(ArithmeticException.class, () -> calculator.divide(a, b));
    }
}
