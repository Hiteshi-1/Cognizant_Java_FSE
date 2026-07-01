package com.cognizant.testing.service;

/**
 * MyService – plain service class used across the basic Mockito Hands-On Exercises 1–7.
 */
public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchDataForUser(String userId) {
        return externalApi.getDataForUser(userId);
    }

    /**
     * Used in Exercise 4: Handling Void Methods.
     */
    public void sendData(String data) {
        externalApi.sendData(data);
    }

    /**
     * Used in Exercise 6: Verifying Interaction Order.
     * Calls getData() first, then sendData() with the result.
     */
    public void fetchThenSend() {
        String data = externalApi.getData();
        externalApi.sendData(data);
    }
}
