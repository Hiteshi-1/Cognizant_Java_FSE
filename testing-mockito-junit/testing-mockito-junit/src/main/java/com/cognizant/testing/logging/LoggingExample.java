package com.cognizant.testing.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LoggingExample – SLF4J Exercise 1: Logging Error Messages and Warning Levels,
 * and Exercise 2: Parameterized Logging.
 */
public class LoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        // Exercise 1: basic error/warn logging
        logger.error("This is an error message");
        logger.warn("This is a warning message");
        logger.info("This is an info message");
        logger.debug("This is a debug message");

        // Exercise 2: parameterized logging
        String user = "Hiteshi";
        int loginAttempts = 3;
        logger.info("User {} attempted to log in {} times", user, loginAttempts);

        // Parameterized logging avoids unnecessary string concatenation when
        // the log level is disabled, and is the recommended SLF4J style.
        logException(new RuntimeException("Sample exception for demo"));
    }

    /**
     * Demonstrates logging an exception with its stack trace.
     */
    public static void logException(Exception e) {
        logger.error("An exception occurred: {}", e.getMessage(), e);
    }
}
