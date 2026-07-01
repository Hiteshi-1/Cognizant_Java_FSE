package com.cognizant.testing.integration;

import com.cognizant.testing.model.User;
import com.cognizant.testing.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Spring Testing Exercise 4: Integration Test with Spring Boot.
 * Tests the full flow: controller → service → repository → H2 in-memory DB.
 *
 * @SpringBootTest loads the full application context.
 * @AutoConfigureMockMvc wires a real MockMvc tied to the running app.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testCreateAndFetchUser_fullFlow() throws Exception {
        // Step 1: Create a user via POST
        User input = new User(null, "IntegrationUser");

        String postResponse = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("IntegrationUser"))
                .andReturn().getResponse().getContentAsString();

        User created = objectMapper.readValue(postResponse, User.class);

        // Step 2: Fetch the created user via GET
        mvc.perform(get("/users/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(created.getId()))
                .andExpect(jsonPath("$.name").value("IntegrationUser"));
    }

    @Test
    void testGetUser_notFound_returnsNull() throws Exception {
        // getUserById() returns null when not found (silent version)
        mvc.perform(get("/users/9999"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUserStrict_notFound_returns404() throws Exception {
        mvc.perform(get("/users/strict/9999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
