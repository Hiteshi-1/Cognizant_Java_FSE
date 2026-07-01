package com.cognizant.testing.service;

/**
 * ApiService – plain service class used in Advanced Mockito Exercise 2.
 */
public class ApiService {

    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String fetchData() {
        return "Fetched " + restClient.getResponse();
    }
}
