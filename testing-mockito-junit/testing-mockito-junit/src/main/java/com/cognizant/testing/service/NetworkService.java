package com.cognizant.testing.service;

/**
 * NetworkService – plain service class used in Advanced Mockito Exercise 4.
 */
public class NetworkService {

    private final NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public String connectToServer() {
        return "Connected to " + networkClient.connect();
    }
}
