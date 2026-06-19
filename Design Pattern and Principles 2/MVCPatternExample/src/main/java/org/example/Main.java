package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        // Create Model
        Student student =
                new Student(
                        "Rahul Sharma",
                        "101",
                        "A");

        // Create View
        StudentView view =
                new StudentView();

        // Create Controller
        StudentController controller =
                new StudentController(
                        student,
                        view);

        // Display Initial Data
        System.out.println("Initial Student Information");
        controller.updateView();

        System.out.println();

        // Update Student Data
        controller.setStudentName("Amit Verma");
        controller.setStudentGrade("A+");

        // Display Updated Data
        System.out.println("Updated Student Information");
        controller.updateView();
    }
}