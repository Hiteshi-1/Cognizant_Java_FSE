package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        // Create Repository Object
        CustomerRepository repository =
                new CustomerRepositoryImpl();

        // Inject Repository into Service
        CustomerService service =
                new CustomerService(repository);

        // Find Customer
        service.getCustomer(101);
        service.getCustomer(102);
        service.getCustomer(200);
    }
}