package com.mihir.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@LocalServerPort
	int serverPort;

	@Test
	void contextLoads() {
	}

	@Test
	void getApiCall() {
		//RestTemplate restTemplate;


		ResponseEntity<Customer> returnedCustomer = testRestTemplate.getForEntity(
				"http://localhost:" + String.valueOf(serverPort) + "/customers/1", Customer.class);

		Assertions.assertEquals(returnedCustomer.getBody().id, "1");
	}

}
