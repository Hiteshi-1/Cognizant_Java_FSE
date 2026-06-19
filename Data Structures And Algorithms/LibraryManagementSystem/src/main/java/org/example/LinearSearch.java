package org.example;

public class LinearSearch {

    public static Book searchByTitle(
            Book[] books,
            String title) {

        for(Book book : books) {

            if(book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }

        return null;
    }
}