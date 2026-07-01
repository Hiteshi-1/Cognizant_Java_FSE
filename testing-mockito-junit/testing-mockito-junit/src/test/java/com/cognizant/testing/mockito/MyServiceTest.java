package com.cognizant.testing.mockito;

import com.cognizant.testing.service.ExternalApi;
import com.cognizant.testing.service.MyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Mockito Hands-On Exercises 1–7.
 */
@ExtendWith(MockitoExtension.class)
class MyServiceTest {

    @Mock
    private ExternalApi mockApi;

    // -------------------------------------------------------
    // Exercise 1: Mocking and Stubbing
    // -------------------------------------------------------
    @Test
    void testExternalApi() {
        when(mockApi.getData()).thenReturn("Mock Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }

    // -------------------------------------------------------
    // Exercise 2: Verifying Interactions
    // -------------------------------------------------------
    @Test
    void testVerifyInteraction() {
        MyService service = new MyService(mockApi);
        service.fetchData();

        verify(mockApi).getData();
    }

    // -------------------------------------------------------
    // Exercise 3: Argument Matching
    // -------------------------------------------------------
    @Test
    void testArgumentMatching() {
        when(mockApi.getDataForUser(anyString())).thenReturn("User Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchDataForUser("user123");

        assertEquals("User Data", result);
        verify(mockApi).getDataForUser(eq("user123"));
    }

    // -------------------------------------------------------
    // Exercise 4: Handling Void Methods
    // -------------------------------------------------------
    @Test
    void testHandlingVoidMethod() {
        doNothing().when(mockApi).sendData(anyString());

        MyService service = new MyService(mockApi);
        service.sendData("test payload");

        verify(mockApi).sendData("test payload");
    }

    // -------------------------------------------------------
    // Exercise 5: Mocking and Stubbing with Multiple Returns
    // -------------------------------------------------------
    @Test
    void testMultipleReturnValues() {
        when(mockApi.getData())
                .thenReturn("First Data")
                .thenReturn("Second Data");

        MyService service = new MyService(mockApi);
        assertEquals("First Data",  service.fetchData());
        assertEquals("Second Data", service.fetchData());
    }

    // -------------------------------------------------------
    // Exercise 6: Verifying Interaction Order
    // -------------------------------------------------------
    @Test
    void testInteractionOrder() {
        when(mockApi.getData()).thenReturn("ordered data");

        MyService service = new MyService(mockApi);
        service.fetchThenSend();

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).sendData("ordered data");
    }

    // -------------------------------------------------------
    // Exercise 7: Handling Void Methods with Exceptions
    // -------------------------------------------------------
    @Test
    void testVoidMethodThrowsException() {
        doThrow(new RuntimeException("Send failed")).when(mockApi).sendData(anyString());

        MyService service = new MyService(mockApi);
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class,
                () -> service.sendData("bad payload"));

        verify(mockApi).sendData("bad payload");
    }
}
