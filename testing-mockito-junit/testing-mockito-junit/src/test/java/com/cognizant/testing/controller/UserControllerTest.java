package com.cognizant.testing.controller;

import com.cognizant.testing.model.User;
import com.cognizant.testing.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Mocking Dependencies in Spring – Exercise 1:
 * Mocking a Service Dependency in a Controller Test.
 *
 * Uses @WebMvcTest to load only the web layer,
 * and @MockBean to inject a Mockito mock of UserService.
 */
@WebMvcTest(controllers = com.cognizant.testing.controller.UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser_returnsMockedUser() throws Exception {
        User mockUser = new User(1L, "Hiteshi");
        when(userService.getUserById(1L)).thenReturn(mockUser);

        mvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Hiteshi"));
    }
}
