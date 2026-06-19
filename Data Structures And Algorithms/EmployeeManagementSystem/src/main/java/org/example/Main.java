package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        EmployeeManagement manager =
                new EmployeeManagement(10);

        manager.addEmployee(
                new Employee(
                        101,
                        "Rahul",
                        "Manager",
                        50000));

        manager.addEmployee(
                new Employee(
                        102,
                        "Amit",
                        "Developer",
                        40000));

        manager.addEmployee(
                new Employee(
                        103,
                        "Priya",
                        "Tester",
                        35000));

        System.out.println(
                "\nEmployee Records:");

        manager.displayEmployees();

        System.out.println(
                "\nSearching Employee 102:");

        System.out.println(
                manager.searchEmployee(
                        102));

        manager.deleteEmployee(
                102);

        System.out.println(
                "\nAfter Deletion:");

        manager.displayEmployees();
    }
}
