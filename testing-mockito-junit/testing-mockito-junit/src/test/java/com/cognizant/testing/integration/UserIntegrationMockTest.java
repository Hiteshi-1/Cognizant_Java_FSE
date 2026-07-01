package com.cognizant.testing.integration;

import com.cognizant.testing.model.User;
import com.cognizant.testing.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Mocking Dependencies in Spring – Exercise 3:
 * Mocking a Service Dependency in an Integration Test.
 *
 * Uses @SpringBootTest (full context) + @AutoConfigureMockMvc,
 * but replaces the real UserService with a @MockBean so the DB is
 * not hit and the response is fully controlled.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationMockTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser_withMockedService_returnsExpectedUser() throws Exception {
        User mockUser = new User(7L, "MockedUser");
        when(userService.getUserById(7L)).thenReturn(mockUser);

        mvc.perform(get("/users/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.name").value("MockedUser"));
    }
}
