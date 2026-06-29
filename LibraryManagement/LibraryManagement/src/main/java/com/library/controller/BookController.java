package com.library.controller;

import com.library.model.Book;
import com.library.repository.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * BookController – REST controller exposing CRUD endpoints for Book management.
 *
 * Exercise 9 : Handles HTTP requests and delegates to the JPA repository.
 *
 * Base URL : http://localhost:8080/api/books
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookJpaRepository bookJpaRepository;

    @Autowired
    public BookController(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    // -------------------------------------------------------
    // CREATE – POST /api/books
    // -------------------------------------------------------

    /**
     * Add a new book.
     * Request body (JSON): { "title": "...", "author": "...", "isbn": "..." }
     */
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book saved = bookJpaRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // -------------------------------------------------------
    // READ ALL – GET /api/books
    // -------------------------------------------------------

    /**
     * Retrieve all books.
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookJpaRepository.findAll();
        return ResponseEntity.ok(books);
    }

    // -------------------------------------------------------
    // READ ONE – GET /api/books/{id}
    // -------------------------------------------------------

    /**
     * Retrieve a single book by its ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookJpaRepository.findById(id);
        return book.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------------------------------
    // UPDATE – PUT /api/books/{id}
    // -------------------------------------------------------

    /**
     * Update an existing book.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookJpaRepository.findById(id).map(existing -> {
            existing.setTitle(bookDetails.getTitle());
            existing.setAuthor(bookDetails.getAuthor());
            existing.setIsbn(bookDetails.getIsbn());
            Book updated = bookJpaRepository.save(existing);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------------------------------
    // DELETE – DELETE /api/books/{id}
    // -------------------------------------------------------

    /**
     * Delete a book by its ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookJpaRepository.existsById(id)) {
            bookJpaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // -------------------------------------------------------
    // SEARCH – GET /api/books/search?keyword=...
    // -------------------------------------------------------

    /**
     * Search books by title keyword.
     */
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        List<Book> results = bookJpaRepository.findByTitleContainingIgnoreCase(keyword);
        return ResponseEntity.ok(results);
    }
}
