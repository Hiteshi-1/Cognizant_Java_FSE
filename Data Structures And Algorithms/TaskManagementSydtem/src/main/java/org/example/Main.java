package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        TaskLinkedList taskList =
                new TaskLinkedList();

        taskList.addTask(
                new Task(
                        101,
                        "Design UI",
                        "Pending"));

        taskList.addTask(
                new Task(
                        102,
                        "Develop Backend",
                        "In Progress"));

        taskList.addTask(
                new Task(
                        103,
                        "Testing",
                        "Pending"));

        System.out.println(
                "All Tasks:");

        taskList.displayTasks();

        System.out.println(
                "\nSearch Task 102:");

        System.out.println(
                taskList.searchTask(102));

        System.out.println();

        taskList.deleteTask(102);

        System.out.println(
                "\nTasks After Deletion:");

        taskList.displayTasks();
    }
}
