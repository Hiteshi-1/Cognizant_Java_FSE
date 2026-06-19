package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        Product p1 =
                new Product(101,
                        "Laptop",
                        50,
                        50000);

        Product p2 =
                new Product(102,
                        "Mouse",
                        100,
                        500);

        Product p3 =
                new Product(103,
                        "Keyboard",
                        80,
                        1200);

        inventory.addProduct(p1);
        inventory.addProduct(p2);
        inventory.addProduct(p3);

        System.out.println("\nInventory:");

        inventory.displayProducts();

        inventory.updateProduct(
                102,
                120,
                550);

        inventory.deleteProduct(103);

        System.out.println("\nUpdated Inventory:");

        inventory.displayProducts();
    }
}
