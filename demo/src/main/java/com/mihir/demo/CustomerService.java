package com.mihir.demo;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

    private Map<String, Customer> customerRepo = new HashMap<>();

    int latestId = 0;

    //CRUD



    public Customer createCustomer(Customer customer) {
        String id = String.valueOf(++latestId);
        customer.setId(id);
        customerRepo.put(id, customer);
        return customer;
    }

    public Customer readCustomer(String id) {
        return customerRepo.get(id);
    }

    public Collection<Customer> readCustomers() {
        return customerRepo.values();
    }

    public Customer updateCustomer(String id, Customer customer) {
        customerRepo.remove(id);
        customer.setId(id);
        customerRepo.put(id, customer);
        return customer;
    }

    public Customer deleteCustomer(String id) {
        Customer temp = customerRepo.get(id);
        customerRepo.remove(id);
        return temp;
    }



}
