package org.example;

public class WebApp
        implements Observer {

    private String userName;

    public WebApp(String userName) {

        this.userName = userName;
    }

    @Override
    public void update(
            String stockName,
            double price) {

        System.out.println(
                "Web App [" +
                        userName +
                        "] - " +
                        stockName +
                        " price updated to ₹" +
                        price);
    }
}