package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Service class for Stock operations.
 * Day 2 HO-2 - Query Methods on stock table
 */
@Service
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;

    /**
     * Returns all Facebook (FB) stock records for September 2019.
     */
    @Transactional
    public List<Stock> getFacebookSeptemberStocks() {
        LOGGER.info("Start getFacebookSeptemberStocks");
        LocalDate startDate = LocalDate.of(2019, 9, 1);
        LocalDate endDate = LocalDate.of(2019, 9, 30);
        return stockRepository.findByCodeAndDateBetween("FB", startDate, endDate);
    }

    /**
     * Returns all Google (GOOGL) stock records where close price > 1250.
     */
    @Transactional
    public List<Stock> getGoogleStocksAbove1250() {
        LOGGER.info("Start getGoogleStocksAbove1250");
        return stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250"));
    }

    /**
     * Returns the top 3 records with the highest trading volume (all stocks).
     */
    @Transactional
    public List<Stock> getTop3HighestVolumeStocks() {
        LOGGER.info("Start getTop3HighestVolumeStocks");
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }

    /**
     * Returns the 3 dates when Netflix (NFLX) stocks had the lowest close price.
     */
    @Transactional
    public List<Stock> getTop3LowestNetflixStocks() {
        LOGGER.info("Start getTop3LowestNetflixStocks");
        return stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
    }
}
