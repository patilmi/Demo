package com.mihir.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@RestController
public class RestDemo {

    private Map<String, Customer> customerRepo = new HashMap<>();


    Customer dave = new Customer("1", "Dave", 2011, 10000);

    Customer brenda = new Customer("2", "Brenda", 2018, 50000);





    @RequestMapping(value= "/customers")
    public ResponseEntity<Object> getCustomer() {

        customerRepo.put(dave.getId(), dave);
        customerRepo.put(brenda.getId(), brenda);
        return new ResponseEntity<>(customerRepo.values(), HttpStatus.OK);
    }



}
