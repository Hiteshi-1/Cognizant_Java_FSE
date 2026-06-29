package com.cognizant.ormlearn.service.exception;

/**
 * Custom exception thrown when a country is not found by its code.
 * Hands-on 6 (Day 1) - Find country by code
 */
public class CountryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException(String message) {
        super(message);
    }
}
