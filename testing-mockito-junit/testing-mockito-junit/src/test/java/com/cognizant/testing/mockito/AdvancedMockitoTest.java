package com.cognizant.testing.mockito;

import com.cognizant.testing.repository.Repository;
import com.cognizant.testing.service.*;
import com.cognizant.testing.util.FileReader;
import com.cognizant.testing.util.FileWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Advanced Mockito Exercises 1–5.
 */
@ExtendWith(MockitoExtension.class)
class AdvancedMockitoTest {

    // -------------------------------------------------------
    // Exercise 1: Mocking Databases and Repositories
    // -------------------------------------------------------
    @Test
    void testServiceWithMockRepository() {
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("Mock Data");

        Service service = new Service(mockRepository);
        String result = service.processData();

        assertEquals("Processed Mock Data", result);
    }

    // -------------------------------------------------------
    // Exercise 2: Mocking External Services (RESTful APIs)
    // -------------------------------------------------------
    @Test
    void testServiceWithMockRestClient() {
        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenReturn("Mock Response");

        ApiService apiService = new ApiService(mockRestClient);
        String result = apiService.fetchData();

        assertEquals("Fetched Mock Response", result);
    }

    // -------------------------------------------------------
    // Exercise 3: Mocking File I/O
    // -------------------------------------------------------
    @Test
    void testServiceWithMockFileIO() {
        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("Mock File Content");

        FileService fileService = new FileService(mockFileReader, mockFileWriter);
        String result = fileService.processFile();

        assertEquals("Processed Mock File Content", result);
        verify(mockFileWriter).write("Processed Mock File Content");
    }

    // -------------------------------------------------------
    // Exercise 4: Mocking Network Interactions
    // -------------------------------------------------------
    @Test
    void testServiceWithMockNetworkClient() {
        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");

        NetworkService networkService = new NetworkService(mockNetworkClient);
        String result = networkService.connectToServer();

        assertEquals("Connected to Mock Connection", result);
    }

    // -------------------------------------------------------
    // Exercise 5: Mocking Multiple Return Values
    // -------------------------------------------------------
    @Test
    void testServiceWithMultipleReturnValues() {
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData())
                .thenReturn("First Mock Data")
                .thenReturn("Second Mock Data");

        Service service = new Service(mockRepository);
        String firstResult  = service.processData();
        String secondResult = service.processData();

        assertEquals("Processed First Mock Data",  firstResult);
        assertEquals("Processed Second Mock Data", secondResult);
    }
}
