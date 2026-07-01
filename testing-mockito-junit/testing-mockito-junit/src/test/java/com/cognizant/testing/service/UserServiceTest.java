package com.cognizant.testing.service;

import com.cognizant.testing.model.User;
import com.cognizant.testing.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Mocking Dependencies in Spring – Exercise 2:
 * Mocking a Repository in a Service Test.
 *
 * Uses pure Mockito (@Mock + @InjectMocks) — no Spring context loaded,
 * so tests are fast and focused on business logic only.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById_returnsUser() {
        User expected = new User(1L, "Hiteshi");
        when(userRepository.findById(1L)).thenReturn(Optional.of(expected));

        User actual = userService.getUserById(1L);

        assertNotNull(actual);
        assertEquals(1L, actual.getId());
        assertEquals("Hiteshi", actual.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserById_returnsNull_whenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        User actual = userService.getUserById(99L);

        assertNull(actual);
        verify(userRepository).findById(99L);
    }

    @Test
    void testSaveUser() {
        User input  = new User(null, "NewUser");
        User saved  = new User(5L,   "NewUser");
        when(userRepository.save(input)).thenReturn(saved);

        User actual = userService.saveUser(input);

        assertNotNull(actual);
        assertEquals(5L, actual.getId());
        verify(userRepository).save(input);
    }
}
