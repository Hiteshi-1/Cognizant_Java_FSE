package com.cognizant.springlearn;

import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * SpringLearnApplication – Entry point for the Cognizant DN 5.0 Spring Core
 * and REST hands-on exercises (Sessions 1–3).
 *
 * Run as a Spring Boot app to expose all REST endpoints:
 *   mvn spring-boot:run
 *
 * To run the classic XML hands-on demos (2, 4, 5, 6), uncomment the
 * relevant method calls in main() below.
 */
@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Start");

        SpringApplication.run(SpringLearnApplication.class, args);

        // Uncomment any of these to run the classic XML-context hands-on demos:
        // displayDate();
        // displayCountry();
        // displayCountries();

        LOGGER.info("End");
    }

    // =========================================================
    // Hands-on 2: Load SimpleDateFormat from Spring Configuration XML
    // =========================================================
    public static void displayDate() {
        LOGGER.info("Start");

        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);

        try {
            Date date = format.parse("31/12/2018");
            LOGGER.debug("Parsed date: {}", date);
        } catch (ParseException e) {
            LOGGER.error("Error parsing date", e);
        }

        LOGGER.info("End");
    }

    // =========================================================
    // Hands-on 4 & 5: Load Country bean + Singleton/Prototype scope demo
    // =========================================================
    public static void displayCountry() {
        LOGGER.info("Start");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("Country : {}", country.toString());

        // Hands-on 5: Singleton scope demo – constructor called only ONCE
        Country anotherCountry = context.getBean("country", Country.class);
        LOGGER.debug("Same instance? {}", (country == anotherCountry));
        // Change scope="prototype" in country.xml to see constructor called TWICE
        // and (country == anotherCountry) become false.

        LOGGER.info("End");
    }

    // =========================================================
    // Hands-on 6: Load list of countries from Spring Configuration XML
    // =========================================================
    @SuppressWarnings("unchecked")
    public static void displayCountries() {
        LOGGER.info("Start");

        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countryList = (List<Country>) context.getBean("countryList", List.class);

        for (Country c : countryList) {
            LOGGER.debug("Country : {}", c.toString());
        }

        LOGGER.info("End");
    }
}
