package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Product[] products = {

                new Product(
                        101,
                        "Laptop",
                        "Electronics"),

                new Product(
                        102,
                        "Mouse",
                        "Accessories"),

                new Product(
                        103,
                        "Keyboard",
                        "Accessories"),

                new Product(
                        104,
                        "Monitor",
                        "Electronics"),

                new Product(
                        105,
                        "Printer",
                        "Office")
        };

        System.out.println(
                "Linear Search Result:");

        Product result1 =
                LinearSearch.search(
                        products,
                        104);

        System.out.println(result1);

        System.out.println();

        System.out.println(
                "Binary Search Result:");

        Product result2 =
                BinarySearch.search(
                        products,
                        104);

        System.out.println(result2);
    }
}
