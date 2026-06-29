package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BookService – Business logic layer for the Library Management application.
 *
 * Exercise 2 : Setter injection wired via applicationContext.xml
 * Exercise 6 : @Service annotation enables component scanning
 * Exercise 7 : Demonstrates BOTH constructor injection and setter injection
 */
@Service
public class BookService {

    // -------------------------------------------------------
    // Exercise 7 – Constructor Injection
    // -------------------------------------------------------
    private BookRepository bookRepository;

    /**
     * Constructor injection (Exercise 7).
     * Spring calls this constructor and passes the BookRepository bean.
     */
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] Constructor injection successful.");
    }

    // -------------------------------------------------------
    // Exercise 2 & 7 – Setter Injection
    // -------------------------------------------------------

    /**
     * Setter injection (Exercise 2, 5, 7).
     * Declared as a setter so Spring / XML config can inject the dependency.
     */
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("[BookService] Setter injection successful.");
    }

    // -------------------------------------------------------
    // Business Methods (targeted by AOP in Ex 3 & 8)
    // -------------------------------------------------------

    public void getAllBooks() {
        System.out.println("[BookService] getAllBooks() called.");
        bookRepository.getAllBooks();
    }

    public void addBook(String title) {
        System.out.println("[BookService] addBook() called for: " + title);
        bookRepository.saveBook(title);
    }
}
