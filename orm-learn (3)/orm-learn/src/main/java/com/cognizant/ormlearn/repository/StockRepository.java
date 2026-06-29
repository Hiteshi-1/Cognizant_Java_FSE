package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

/**
 * Repository for Stock entity.
 *
 * Hands-on 2 (Day 2) - Query Methods on stock table:
 *  1. findByCodeAndDateBetween       → Facebook stocks in September 2019
 *  2. findByCodeAndCloseGreaterThan  → Google stocks where close > 1250
 *  3. findTop3ByOrderByVolumeDesc    → Top 3 highest volume dates
 *  4. findTop3ByCodeOrderByCloseAsc  → 3 lowest Netflix closing prices
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    /**
     * Get all stock records for a given code between two dates.
     * Usage: findByCodeAndDateBetween("FB", start, end)
     */
    List<Stock> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    /**
     * Get all stock records for a given code where close price is greater than a threshold.
     * Usage: findByCodeAndCloseGreaterThan("GOOGL", 1250)
     */
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal close);

    /**
     * Top 3 records across all stocks with highest volume (all stocks).
     */
    List<Stock> findTop3ByOrderByVolumeDesc();

    /**
     * Top 3 records for a given stock code with the lowest close price.
     * Usage: findTop3ByCodeOrderByCloseAsc("NFLX")
     */
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
