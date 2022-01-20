package com.mihir.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@RestController
public class RestDemo {

    private Map<String, Customer> customerRepo = new HashMap<>();
    int latestId = 0;

    @Autowired
    private CustomerService customerService;

    @PostConstruct
    private void initializer () {
        Customer dave = new Customer("1", "Dave", 2011, 10000);
        Customer brenda = new Customer("2", "Brenda", 2018, 50000);

        customerRepo.put(dave.getId(), dave);
        customerRepo.put(brenda.getId(), brenda);
        latestId = 2;
    }



    @RequestMapping(value= "/customers")
    @ResponseBody
    public Collection<Customer> getCustomer() {
        return customerRepo.values();
    }

    @RequestMapping(value= "/customers/{id}")
    @ResponseBody
    public Customer getCustomer(@PathVariable("id") String id) {
        if (customerRepo.containsKey(id)) {
            return customerRepo.get(id);
        }

        throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        String id = String.valueOf(++latestId);
        customer.setId(id);
        customerRepo.put(id, customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {

        if (customerRepo.containsKey(id)) {
            customerRepo.remove(id);
            customer.setId(id);
            customerRepo.put(id, customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public Customer delete(@PathVariable("id") String id) {
        if (customerRepo.containsKey(id)) {
            Customer temp = customerRepo.get(id);
            customerRepo.remove(id);
            return temp;
        }

        throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");

    }



}
