package com.cognizant.testing.service;

import org.springframework.stereotype.Service;

/**
 * CalculatorService – Spring Testing Exercise 1: Basic Unit Test for a Service Method.
 */
@Service
public class CalculatorService {

    public int add(int a, int b) {
        return a + b;
    }
}
