package com.cognizant.testing.repository;

import com.cognizant.testing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * UserRepository – Spring Data JPA repository for User.
 * Spring Testing Exercise 7: custom query method findByName().
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);
}
