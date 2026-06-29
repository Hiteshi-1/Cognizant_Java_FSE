-- ============================================================
--  stock-setup.sql
--  Creates the stock table for Hands-on 2 (Day 2).
--  Load data from stock-data.sql (provided in Cognizant files)
--  using: mysql> source /path/to/stock-data.sql
-- ============================================================

USE ormlearn;

CREATE TABLE IF NOT EXISTS `ormlearn`.`stock` (
    `st_id`     INT          NOT NULL AUTO_INCREMENT,
    `st_code`   VARCHAR(10),
    `st_date`   DATE,
    `st_open`   NUMERIC(10, 2),
    `st_close`  NUMERIC(10, 2),
    `st_volume` NUMERIC,
    PRIMARY KEY (`st_id`)
);

-- After creating the table, load stock data:
-- mysql> source D:\spring-data-jpa-files\stock-data.sql
