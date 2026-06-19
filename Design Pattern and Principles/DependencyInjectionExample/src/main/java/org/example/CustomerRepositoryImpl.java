package org.example;

public class CustomerRepositoryImpl
        implements CustomerRepository {

    @Override
    public String findCustomerById(int id) {

        if(id == 101) {
            return "Rahul Sharma";
        }

        if(id == 102) {
            return "Amit Verma";
        }

        return "Customer Not Found";
    }
}
