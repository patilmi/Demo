package com.mihir.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class RestDemoTest {

    private RestDemo restDemo = new RestDemo();
    private CustomerService customerService = Mockito.mock(CustomerService.class);
    private Customer mockCustomer = Mockito.mock(Customer.class);
    private Customer mockCustomer2 = Mockito.mock(Customer.class);

    @BeforeEach
    void setUp() {
        restDemo.customerService = customerService;
        Mockito.when(customerService.createCustomer(mockCustomer)).thenReturn(mockCustomer2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCustomer() {
    }

    @Test
    void testGetCustomer() {
    }

    @Test
    void createCustomer() {
       ResponseEntity<Customer> createdResponse = restDemo.createCustomer(mockCustomer);
       Mockito.verify(createdResponse.getBody()).equals(mockCustomer2);
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void delete() {
    }
}