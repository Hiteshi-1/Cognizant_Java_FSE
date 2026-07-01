package com.cognizant.testing.service;

/**
 * ExternalApi – plain interface used in the basic Mockito Hands-On Exercises
 * (Mocking and Stubbing, Verifying Interactions, Argument Matching, etc.)
 */
public interface ExternalApi {
    String getData();
    String getDataForUser(String userId);
    void sendData(String data);
    int fetchCount();
}
