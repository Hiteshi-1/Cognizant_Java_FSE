package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        Order[] orders = {

                new Order(
                        101,
                        "Rahul",
                        5000),

                new Order(
                        102,
                        "Amit",
                        2500),

                new Order(
                        103,
                        "Sneha",
                        8000),

                new Order(
                        104,
                        "Priya",
                        4000)
        };

        System.out.println(
                "Before Sorting:");

        for(Order order : orders) {
            System.out.println(order);
        }

        QuickSort.quickSort(
                orders,
                0,
                orders.length - 1);

        System.out.println(
                "\nAfter Sorting:");

        for(Order order : orders) {
            System.out.println(order);
        }
    }
}