package com.library;

import com.library.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * LibraryManagementApplication – Entry point for the Library Management System.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *  HOW TO RUN
 * ─────────────────────────────────────────────────────────────────────────────
 *  Option A – Spring Boot (Exercise 9)
 *      mvn spring-boot:run
 *      → REST API available at http://localhost:8080/api/books
 *      → H2 Console at       http://localhost:8080/h2-console
 *
 *  Option B – Classic XML Context (Exercises 1–8)
 *      Uncomment the runClassicSpringContext() call below and comment out
 *      SpringApplication.run(...) to test the XML-based bean configuration.
 * ─────────────────────────────────────────────────────────────────────────────
 */
@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {

        // ── Exercise 9: Spring Boot startup ──────────────────────────────────
        SpringApplication.run(LibraryManagementApplication.class, args);

        // ── Exercises 1–8: Classic Spring XML context (uncomment to test) ────
        // runClassicSpringContext();
    }

    /**
     * Loads the XML-based ApplicationContext and exercises the beans.
     *
     * Covers:
     *   Exercise 1  – loading Spring context & bean retrieval
     *   Exercise 2  – setter-injected BookRepository inside BookService
     *   Exercise 3  – AOP logging of execution times (observe console output)
     *   Exercise 5  – same as Ex 1/2 via XML config
     *   Exercise 6  – annotation-based beans picked up by component-scan
     *   Exercise 7  – constructor + setter injection
     *   Exercise 8  – @Before / @After / @AfterReturning advice in console
     */
    private static void runClassicSpringContext() {
        System.out.println("=== Loading Classic Spring ApplicationContext (Exercises 1–8) ===");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = (BookService) context.getBean("bookService");

        System.out.println("\n--- Calling getAllBooks() ---");
        bookService.getAllBooks();

        System.out.println("\n--- Calling addBook() ---");
        bookService.addBook("Clean Code by Robert C. Martin");

        System.out.println("\n=== Classic Spring context test complete ===");

        ((ClassPathXmlApplicationContext) context).close();
    }
}
