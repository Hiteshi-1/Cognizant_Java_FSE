package com.cognizant.testing.suite;

import com.cognizant.testing.junit.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Advanced JUnit Exercise 2: Test Suites and Categories.
 * Groups all JUnit test classes into one suite.
 */
@Suite
@SelectClasses({
    AssertionsTest.class,
    CalculatorTest.class,
    EvenCheckerTest.class,
    OrderedTests.class,
    ExceptionThrowerTest.class,
    PerformanceTesterTest.class
})
public class AllTests {}
