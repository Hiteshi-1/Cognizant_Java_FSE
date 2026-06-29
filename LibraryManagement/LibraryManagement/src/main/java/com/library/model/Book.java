package com.library.model;

import jakarta.persistence.*;

/**
 * Book – JPA Entity for the Library Management system.
 *
 * Exercise 9 : Mapped to the BOOK table in the H2 in-memory database.
 */
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column
    private String isbn;

    // -------------------------------------------------------
    // Constructors
    // -------------------------------------------------------

    public Book() {}

    public Book(String title, String author, String isbn) {
        this.title  = title;
        this.author = author;
        this.isbn   = isbn;
    }

    // -------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------

    public Long getId()                    { return id; }
    public void setId(Long id)             { this.id = id; }

    public String getTitle()               { return title; }
    public void setTitle(String title)     { this.title = title; }

    public String getAuthor()              { return author; }
    public void setAuthor(String author)   { this.author = author; }

    public String getIsbn()                { return isbn; }
    public void setIsbn(String isbn)       { this.isbn = isbn; }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "', isbn='" + isbn + "'}";
    }
}
