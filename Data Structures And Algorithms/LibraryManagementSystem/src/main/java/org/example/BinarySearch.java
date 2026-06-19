package org.example;

public class BinarySearch {

    public static Book searchByTitle(
            Book[] books,
            String title) {

        int left = 0;
        int right = books.length - 1;

        while(left <= right) {

            int mid =
                    left + (right - left) / 2;

            int comparison =
                    books[mid].title
                            .compareToIgnoreCase(title);

            if(comparison == 0) {

                return books[mid];
            }

            if(comparison < 0) {

                left = mid + 1;
            }
            else {

                right = mid - 1;
            }
        }

        return null;
    }
}
