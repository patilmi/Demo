package com.mihir.demo;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

    private Map<String, Customer> customerRepo = new HashMap<>();

    int latestId = 0;


    @PostConstruct
    private void initializer () {
        Customer dave = new Customer("1", "Dave", 2011, 10000);
        Customer brenda = new Customer("2", "Brenda", 2018, 50000);

        customerRepo.put(dave.getId(), dave);
        customerRepo.put(brenda.getId(), brenda);
        latestId = 2;
    }


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
        if (customerRepo.containsKey(id)) {
            customer.setId(id);
            customerRepo.put(id, customer);
            return customer;
        }

        return null;
    }

    public Customer deleteCustomer(String id) {
        Customer deletedCustomer = customerRepo.get(id);
        customerRepo.remove(id);
        return deletedCustomer;
    }



}
