package com.cognizant.springlearn.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Country – Model bean for Hands-on 4, 5, 6 and REST sessions.
 */
public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    @NotNull
    @Size(min = 2, max = 2, message = "Country code should be 2 characters")
    private String code;

    @NotNull
    @Size(min = 1, max = 50, message = "Country name should be between 1 and 50 characters")
    private String name;

    // -------------------------------------------------------
    // Hands-on 4: Empty constructor with debug log
    // -------------------------------------------------------
    public Country() {
        LOGGER.debug("Inside Country Constructor.");
    }

    // -------------------------------------------------------
    // Getters & Setters with debug logs (Hands-on 4)
    // -------------------------------------------------------

    public String getCode() {
        LOGGER.debug("Inside getCode(). code = {}", code);
        return code;
    }

    public void setCode(String code) {
        LOGGER.debug("Inside setCode(). code = {}", code);
        this.code = code;
    }

    public String getName() {
        LOGGER.debug("Inside getName(). name = {}", name);
        return name;
    }

    public void setName(String name) {
        LOGGER.debug("Inside setName(). name = {}", name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{code='" + code + "', name='" + name + "'}";
    }
}
