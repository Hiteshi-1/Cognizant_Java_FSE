package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Book[] books = {

                new Book(
                        101,
                        "Algorithms",
                        "Thomas Cormen"),

                new Book(
                        102,
                        "Data Structures",
                        "Mark Weiss"),

                new Book(
                        103,
                        "Java Programming",
                        "Herbert Schildt"),

                new Book(
                        104,
                        "Python Basics",
                        "John Smith"),

                new Book(
                        105,
                        "Web Development",
                        "David Miller")
        };

        System.out.println(
                "Linear Search:");

        Book result1 =
                LinearSearch.searchByTitle(
                        books,
                        "Java Programming");

        System.out.println(result1);

        System.out.println();

        System.out.println(
                "Binary Search:");

        Book result2 =
                BinarySearch.searchByTitle(
                        books,
                        "Java Programming");

        System.out.println(result2);
    }
}
