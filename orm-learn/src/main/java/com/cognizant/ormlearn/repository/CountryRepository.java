package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Country entity.
 *
 * Hands-on 1 (Day 2) - Query Methods on country table:
 *  1. findByNameContaining           → search countries by partial name
 *  2. findByNameContainingOrderByName → same but sorted ascending
 *  3. findByNameStartingWith         → countries starting with given letter
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    /**
     * Returns all countries whose name contains the given text (case-sensitive depends on DB collation).
     * Example: findByNameContaining("ou") → Bouvet Island, Djibouti, Guadeloupe …
     */
    List<Country> findByNameContaining(String name);

    /**
     * Same as above but sorted alphabetically by country name (ascending).
     */
    List<Country> findByNameContainingOrderByName(String name);

    /**
     * Returns all countries whose name starts with the given prefix.
     * Example: findByNameStartingWith("Z") → Zambia, Zimbabwe
     */
    List<Country> findByNameStartingWith(String prefix);
}
