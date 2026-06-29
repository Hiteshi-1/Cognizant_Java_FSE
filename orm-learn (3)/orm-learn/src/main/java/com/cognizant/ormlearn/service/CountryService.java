package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Country operations.
 *
 * Covers:
 *  Day 1 HO-1 : getAllCountries()
 *  Day 1 HO-5 : findCountryByCode(), addCountry(), updateCountry(), deleteCountry()
 *  Day 2 HO-1 : searchByName(), searchByNameSorted(), searchByStartingLetter()
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    // ── Day 1 HO-1 ────────────────────────────────────────────────────────

    /**
     * Returns all countries from the database.
     */
    @Transactional
    public List<Country> getAllCountries() {
        LOGGER.info("Start getAllCountries");
        return countryRepository.findAll();
    }

    // ── Day 1 HO-6 ────────────────────────────────────────────────────────

    /**
     * Finds a single country by its 2-letter code.
     *
     * @throws CountryNotFoundException when the code doesn't match any record
     */
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        LOGGER.info("Start findCountryByCode: {}", countryCode);
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code: " + countryCode);
        }
        Country country = result.get();
        LOGGER.debug("Country found: {}", country);
        return country;
    }

    // ── Day 1 HO-7 ────────────────────────────────────────────────────────

    /**
     * Persists a new country record.
     */
    @Transactional
    public void addCountry(Country country) {
        LOGGER.info("Start addCountry: {}", country);
        countryRepository.save(country);
        LOGGER.info("End addCountry");
    }

    // ── Day 1 HO-8 ────────────────────────────────────────────────────────

    /**
     * Updates the name of an existing country identified by code.
     */
    @Transactional
    public void updateCountry(String code, String newName) throws CountryNotFoundException {
        LOGGER.info("Start updateCountry: code={}, newName={}", code, newName);
        Optional<Country> result = countryRepository.findById(code);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found for code: " + code);
        }
        Country country = result.get();
        country.setName(newName);
        countryRepository.save(country);
        LOGGER.info("End updateCountry");
    }

    // ── Day 1 HO-9 ────────────────────────────────────────────────────────

    /**
     * Deletes the country with the given code.
     */
    @Transactional
    public void deleteCountry(String code) {
        LOGGER.info("Start deleteCountry: {}", code);
        countryRepository.deleteById(code);
        LOGGER.info("End deleteCountry");
    }

    // ── Day 2 HO-1 Query Methods ──────────────────────────────────────────

    /**
     * Returns countries whose name contains the given text (for live search).
     */
    @Transactional
    public List<Country> searchByName(String keyword) {
        LOGGER.info("Start searchByName: {}", keyword);
        return countryRepository.findByNameContaining(keyword);
    }

    /**
     * Returns countries whose name contains the given text, sorted A-Z.
     */
    @Transactional
    public List<Country> searchByNameSorted(String keyword) {
        LOGGER.info("Start searchByNameSorted: {}", keyword);
        return countryRepository.findByNameContainingOrderByName(keyword);
    }

    /**
     * Returns countries whose name starts with the given letter/prefix.
     */
    @Transactional
    public List<Country> searchByStartingLetter(String prefix) {
        LOGGER.info("Start searchByStartingLetter: {}", prefix);
        return countryRepository.findByNameStartingWith(prefix);
    }
}
