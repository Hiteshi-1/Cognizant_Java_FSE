package com.cognizant.testing.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AppenderDemo – SLF4J Exercise 3: Using Different Appenders.
 *
 * Logs are routed to both the CONSOLE and FILE appenders configured in
 * src/main/resources/logback.xml (writes to logs/app.log).
 */
public class AppenderDemo {

    private static final Logger logger = LoggerFactory.getLogger(AppenderDemo.class);

    public static void main(String[] args) {
        logger.info("Application started — this line goes to both console and logs/app.log");
        logger.debug("Debug-level detail, visible because com.cognizant.testing logger is set to DEBUG");
        logger.warn("A warning routed through both appenders");
        logger.error("An error routed through both appenders");
    }
}
