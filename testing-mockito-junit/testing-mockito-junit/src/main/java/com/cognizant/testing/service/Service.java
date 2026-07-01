package com.cognizant.testing.service;

import com.cognizant.testing.repository.Repository;

/**
 * Service – plain service class used in Advanced Mockito Exercise 1 & 5.
 * Not a Spring bean; constructed directly with a Repository dependency
 * to keep the original hands-on's plain-Mockito style intact.
 */
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        return "Processed " + repository.getData();
    }
}
