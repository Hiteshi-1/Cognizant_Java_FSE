package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CountryController – REST endpoints for Country resource.
 *
 * Follows RESTful naming convention: base URL "/countries" (plural resource name).
 * The legacy singular endpoints ("/country", "/country/{code}") are also kept
 * to match the original hands-on instructions exactly.
 */
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    public CountryController() {
        LOGGER.debug("Inside CountryController Constructor.");
    }

    // -------------------------------------------------------
    // Legacy singular endpoints (as specified in hands-on doc)
    // -------------------------------------------------------

    /** GET /country – returns India's details */
    @GetMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("Start");
        Country country = countryService.getCountryIndia();
        LOGGER.info("End");
        return country;
    }

    /** GET /country/{code} – case-insensitive lookup */
    @GetMapping("/country/{code}")
    public Country getCountryByCode(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("Start");
        Country country = countryService.getCountry(code);
        LOGGER.info("End");
        return country;
    }

    /** GET /countries – returns all four countries */
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("End");
        return countries;
    }

    // -------------------------------------------------------
    // RESTful resource-naming convention versions
    // -------------------------------------------------------

    /** GET /countries/{code} – RESTful plural lookup */
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
        LOGGER.info("Start");
        Country country = countryService.getCountry(code);
        LOGGER.info("End");
        return country;
    }

    /** POST /countries – create a country (with validation) */
    @PostMapping("/countries")
    public Country addCountry(@RequestBody @Valid Country country) {
        LOGGER.info("Start");
        LOGGER.debug("Received country: {}", country);
        LOGGER.info("End");
        return country;
    }

    /** PUT /countries – update a country (payload contains data) */
    @PutMapping("/countries")
    public Country updateCountry(@RequestBody @Valid Country country) {
        LOGGER.info("Start");
        LOGGER.debug("Updating country: {}", country);
        LOGGER.info("End");
        return country;
    }

    /** DELETE /countries/{code} – delete a specific country */
    @DeleteMapping("/countries/{code}")
    public void deleteCountry(@PathVariable String code) {
        LOGGER.info("Start");
        LOGGER.debug("Deleting country with code: {}", code);
        LOGGER.info("End");
    }
}
