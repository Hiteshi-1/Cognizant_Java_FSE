package com.cognizant.testing.service;

import com.cognizant.testing.model.User;
import com.cognizant.testing.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * UserService – business logic for User operations.
 * Used across Spring Testing Exercises 2–9 and the Mockito-in-Spring exercises.
 */
@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        LOGGER.debug("Fetching user with id {}", id);
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Spring Testing Exercise 6: Test Service Exception Handling.
     * Throws NoSuchElementException when the user is not found, instead
     * of silently returning null, so the global exception handler (Exercise 8)
     * has something to catch.
     */
    public User getUserByIdOrThrow(Long id) {
        LOGGER.debug("Fetching user with id {} (throwing variant)", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public User saveUser(User user) {
        LOGGER.debug("Saving user {}", user);
        return userRepository.save(user);
    }

    /**
     * Spring Testing Exercise 7: Test Custom Repository Query.
     */
    public List<User> getUsersByName(String name) {
        LOGGER.debug("Fetching users with name {}", name);
        return userRepository.findByName(name);
    }
}
