package org.example;

public class Main {

    public static void main(String[] args) {

        Computer gamingPC =
                new Computer.Builder()
                        .setCPU("Intel i9")
                        .setRAM("32GB")
                        .setStorage("1TB SSD")
                        .build();

        Computer officePC =
                new Computer.Builder()
                        .setCPU("Intel i5")
                        .setRAM("8GB")
                        .setStorage("256GB SSD")
                        .build();

        System.out.println("Gaming PC:");
        gamingPC.display();

        System.out.println();

        System.out.println("Office PC:");
        officePC.display();
    }
}