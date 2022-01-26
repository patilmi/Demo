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
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class RestDemo {

    @Autowired
    public CustomerService customerService;

//    @RequestMapping(value= "/customers")
//    @ResponseBody
//    public Collection<Customer> getCustomer() {
//        return customerService.readCustomers();
//    }

    @RequestMapping(value= "/customers/{id}")
    @ResponseBody
    public Customer getCustomer(@PathVariable("id") String id) {
        Customer customer = customerService.readCustomer(id);
        if (customer == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return customer;
    }

    @RequestMapping(value = "/customers", method = POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers/{id}", method = PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
        Customer replacedCustomer = customerService.updateCustomer(id, customer);

        if (replacedCustomer == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return new ResponseEntity<>(replacedCustomer, OK);
    }

    @RequestMapping(value = "/customers/{id}", method = DELETE)
    public Customer delete(@PathVariable("id") String id) {
        Customer deletedCustomer = customerService.deleteCustomer(id);
        if (deletedCustomer == null) {

            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return deletedCustomer;
    }

}
