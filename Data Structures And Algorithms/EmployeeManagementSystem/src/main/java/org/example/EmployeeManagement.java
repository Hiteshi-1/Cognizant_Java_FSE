package org.example;

public class EmployeeManagement {

    private Employee[] employees;
    private int count;

    public EmployeeManagement(int size) {

        employees = new Employee[size];
        count = 0;
    }

    // Add Employee
    public void addEmployee(Employee employee) {

        if(count < employees.length) {

            employees[count] = employee;
            count++;

            System.out.println(
                    "Employee Added");
        }
        else {

            System.out.println(
                    "Array Full");
        }
    }

    // Search Employee
    public Employee searchEmployee(
            int employeeId) {

        for(int i = 0;
            i < count;
            i++) {

            if(employees[i].employeeId
                    == employeeId) {

                return employees[i];
            }
        }

        return null;
    }

    // Traverse Employees
    public void displayEmployees() {

        for(int i = 0;
            i < count;
            i++) {

            System.out.println(
                    employees[i]);
        }
    }

    // Delete Employee
    public void deleteEmployee(
            int employeeId) {

        int index = -1;

        for(int i = 0;
            i < count;
            i++) {

            if(employees[i].employeeId
                    == employeeId) {

                index = i;
                break;
            }
        }

        if(index == -1) {

            System.out.println(
                    "Employee Not Found");

            return;
        }

        for(int i = index;
            i < count - 1;
            i++) {

            employees[i]
                    = employees[i + 1];
        }

        employees[count - 1] = null;

        count--;

        System.out.println(
                "Employee Deleted");
    }
}