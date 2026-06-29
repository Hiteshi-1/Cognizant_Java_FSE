package com.library.repository;

import org.springframework.stereotype.Repository;

/**
 * BookRepository – Data access layer for the Library Management application.
 *
 * Exercise 1 : Plain POJO bean (declared in applicationContext.xml)
 * Exercise 6 : @Repository annotation enables component scanning
 */
@Repository
public class BookRepository {

    /**
     * Simulates fetching all books from a data store.
     * (In Ex 9 this is replaced by a JPA repository interface.)
     */
    public void getAllBooks() {
        System.out.println("[BookRepository] Fetching all books from the data store...");
    }

    /**
     * Simulates saving a book to the data store.
     */
    public void saveBook(String title) {
        System.out.println("[BookRepository] Saving book: " + title);
    }
}
