package com.mihir.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@RestController
public class RestDemo {

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



    @RequestMapping(value= "/customers")
    public ResponseEntity<Object> getCustomer() {
        return new ResponseEntity<>(customerRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        String id = String.valueOf(++latestId);
        customer.setId(id);
        customerRepo.put(id, customer);
        return new ResponseEntity<>("Customer is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
        customerRepo.remove(id);
        customer.setId(id);
        customerRepo.put(id, customer);
        return new ResponseEntity<>("Customer is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        customerRepo.remove(id);
        return new ResponseEntity<>("Customer is deleted successfully", HttpStatus.OK);
    }



}
