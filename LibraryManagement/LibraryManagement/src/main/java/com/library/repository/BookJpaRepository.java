package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BookJpaRepository – Spring Data JPA repository for the Book entity.
 *
 * Exercise 9 : Extends JpaRepository to get full CRUD out of the box.
 *              Spring Boot auto-implements this interface at runtime.
 */
@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {

    // Custom derived query: find books by author name
    List<Book> findByAuthor(String author);

    // Custom derived query: find by title containing a keyword (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String keyword);
}
