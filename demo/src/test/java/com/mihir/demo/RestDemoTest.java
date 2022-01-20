package com.mihir.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class RestDemoTest {

    private RestDemo restDemo = new RestDemo();
    private CustomerService customerService = Mockito.mock(CustomerService.class);
    private Customer mockCustomer = Mockito.mock(Customer.class);
    private Customer mockCustomer2 = Mockito.mock(Customer.class);

    private String customerId1 = "1";
    private String customerId2 = "2";

    @BeforeEach
    void setUp() {
        restDemo.customerService = customerService;

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCustomer() {
        Mockito.when(customerService.readCustomer(customerId2)).thenReturn(mockCustomer2);
        Customer getResponse = restDemo.getCustomer(customerId2);
        verify(customerService).readCustomer(customerId2);
        assertEquals(getResponse, mockCustomer2);
    }


    @Test
    void createCustomer() {
        Mockito.when(customerService.createCustomer(mockCustomer)).thenReturn(mockCustomer2);
        ResponseEntity<Customer> createdResponse = restDemo.createCustomer(mockCustomer);
        verify(customerService).createCustomer(mockCustomer);
        assertEquals(createdResponse.getBody(), mockCustomer2);

    }

    @Test
    void updateCustomer() {
        Mockito.when(customerService.updateCustomer(customerId2, mockCustomer2)).thenReturn(mockCustomer2);
        ResponseEntity<Customer> updatedResponse = restDemo.updateCustomer(customerId2, mockCustomer2);
        verify(customerService).updateCustomer(customerId2, mockCustomer2);
        assertEquals(updatedResponse.getBody(), mockCustomer2);
    }

    @Test
    void delete() {
        Mockito.when(customerService.deleteCustomer(customerId1)).thenReturn(mockCustomer);
        Customer deletedResponse = restDemo.delete(customerId1);
        verify(customerService).deleteCustomer(customerId1);
        assertEquals(deletedResponse, mockCustomer);

    }
}