package com.cognizant.testing.controller;

import com.cognizant.testing.model.User;
import com.cognizant.testing.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Spring Testing Exercises 3, 5, 8 – MockMvc controller tests.
 *
 * @WebMvcTest loads only the web layer (controller + exception handler),
 * while the UserService is mocked with @MockBean.
 */
@WebMvcTest(controllers = com.cognizant.testing.controller.UserController.class)
class SpringControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    // -------------------------------------------------------
    // Exercise 3: Testing a REST Controller with MockMvc (GET)
    // -------------------------------------------------------
    @Test
    void testGetUser_returnsUser() throws Exception {
        User user = new User(1L, "Hiteshi");
        when(userService.getUserById(1L)).thenReturn(user);

        mvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Hiteshi"));
    }

    // -------------------------------------------------------
    // Exercise 5: Test Controller POST Endpoint
    // -------------------------------------------------------
    @Test
    void testCreateUser_returnsCreatedUser() throws Exception {
        User input  = new User(null, "NewUser");
        User saved  = new User(10L, "NewUser");
        when(userService.saveUser(any(User.class))).thenReturn(saved);

        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.name").value("NewUser"));
    }

    // -------------------------------------------------------
    // Exercise 8: Test Controller Exception Handling (GlobalExceptionHandler)
    // -------------------------------------------------------
    @Test
    void testGetUserStrict_notFound_returns404() throws Exception {
        when(userService.getUserByIdOrThrow(99L))
                .thenThrow(new NoSuchElementException("User not found with id: 99"));

        mvc.perform(get("/users/strict/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
