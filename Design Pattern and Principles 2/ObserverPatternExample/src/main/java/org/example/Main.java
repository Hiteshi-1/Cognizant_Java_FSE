package org.example;

public class Main {

    public static void main(String[] args) {

        StockMarket stockMarket =
                new StockMarket();

        Observer mobileUser =
                new MobileApp("Rahul");

        Observer webUser =
                new WebApp("Priya");

        stockMarket.registerObserver(
                mobileUser);

        stockMarket.registerObserver(
                webUser);

        System.out.println(
                "Stock Price Updated:");

        stockMarket.setStockPrice(
                "TCS",
                4200);

        System.out.println();

        System.out.println(
                "Removing Web App Observer");

        stockMarket.deregisterObserver(
                webUser);

        System.out.println();

        stockMarket.setStockPrice(
                "Infosys",
                1800);
    }
}