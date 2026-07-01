package com.cognizant.testing.service;

import com.cognizant.testing.model.User;
import com.cognizant.testing.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Spring Testing Exercises 1–2, 6–7, 9 (service-layer / unit-level).
 *
 * Exercises 3, 4, 5, 8 (controller/MockMvc) are in SpringControllerTest.java.
 */
@ExtendWith(MockitoExtension.class)
class SpringServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // -------------------------------------------------------
    // Exercise 1: Basic Unit Test for a Service Method
    // -------------------------------------------------------
    @Test
    void testCalculatorServiceAdd() {
        CalculatorService calculatorService = new CalculatorService();
        assertEquals(7, calculatorService.add(3, 4));
        assertEquals(0, calculatorService.add(-1, 1));
    }

    // -------------------------------------------------------
    // Exercise 2: Mocking a Repository in a Service Test
    // -------------------------------------------------------
    @Test
    void testGetUserById_found() {
        User user = new User(1L, "Hiteshi");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("Hiteshi", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetUserById_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        User result = userService.getUserById(99L);

        assertNull(result);
    }

    // -------------------------------------------------------
    // Exercise 6: Test Service Exception Handling
    // -------------------------------------------------------
    @Test
    void testGetUserByIdOrThrow_throwsWhenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> userService.getUserByIdOrThrow(99L));
    }

    // -------------------------------------------------------
    // Exercise 7: Test Custom Repository Query (findByName)
    // -------------------------------------------------------
    @Test
    void testGetUsersByName() {
        List<User> users = List.of(new User(1L, "Alice"), new User(2L, "Alice"));
        when(userRepository.findByName("Alice")).thenReturn(users);

        List<User> result = userService.getUsersByName("Alice");

        assertEquals(2, result.size());
        verify(userRepository).findByName("Alice");
    }

    // -------------------------------------------------------
    // Exercise 9: Parameterized Test with JUnit
    // -------------------------------------------------------
    @ParameterizedTest
    @CsvSource({
            "1, Alice",
            "2, Bob",
            "3, Carol"
    })
    void testGetUserById_parameterized(Long id, String name) {
        when(userRepository.findById(id)).thenReturn(Optional.of(new User(id, name)));

        User result = userService.getUserById(id);

        assertNotNull(result);
        assertEquals(name, result.getName());
    }
}
