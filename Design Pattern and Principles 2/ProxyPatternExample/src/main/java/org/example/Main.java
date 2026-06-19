package org.example;

public class Main {

    public static void main(String[] args) {

        Image image =
                new ProxyImage(
                        "nature.jpg");

        System.out.println(
                "Image created");

        System.out.println();

        image.display();

        System.out.println();

        image.display();
    }
}