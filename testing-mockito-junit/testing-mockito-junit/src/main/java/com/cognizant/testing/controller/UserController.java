package com.cognizant.testing.controller;

import com.cognizant.testing.model.User;
import com.cognizant.testing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UserController – REST endpoints for User resource.
 * Spring Testing Exercises 3, 5, 8.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        LOGGER.debug("GET /users/{}", id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Variant that throws NoSuchElementException for the missing-user
     * scenario, caught by GlobalExceptionHandler (Exercise 8).
     */
    @GetMapping("/strict/{id}")
    public ResponseEntity<User> getUserStrict(@PathVariable Long id) {
        LOGGER.debug("GET /users/strict/{}", id);
        return ResponseEntity.ok(userService.getUserByIdOrThrow(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        LOGGER.debug("POST /users body={}", user);
        return ResponseEntity.ok(userService.saveUser(user));
    }
}
