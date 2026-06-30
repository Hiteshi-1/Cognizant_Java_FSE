package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CountryService – Business logic for country REST endpoints.
 * REST Session: getCountry(), getAllCountries()
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    public Country getCountryIndia() {
        LOGGER.info("Start");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("in", Country.class);
        LOGGER.info("End");
        return country;
    }

    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> list = (List<Country>) context.getBean("countryList", List.class);
        LOGGER.info("End");
        return list;
    }

    /**
     * REST session – Get country by code (case-insensitive).
     * Throws CountryNotFoundException if code is not in the list.
     */
    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("Start");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> list = (List<Country>) context.getBean("countryList", List.class);

        Country result = list.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(CountryNotFoundException::new);

        LOGGER.debug("Found country: {}", result);
        LOGGER.info("End");
        return result;
    }
}
