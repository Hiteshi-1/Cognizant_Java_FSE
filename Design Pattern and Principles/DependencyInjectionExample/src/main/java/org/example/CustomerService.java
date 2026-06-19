package org.example;

public class CustomerService {

    private CustomerRepository repository;

    // Constructor Injection
    public CustomerService(
            CustomerRepository repository) {

        this.repository = repository;
    }

    public void getCustomer(int id) {

        String customer =
                repository.findCustomerById(id);

        System.out.println(
                "Customer Details: " + customer);
    }
}