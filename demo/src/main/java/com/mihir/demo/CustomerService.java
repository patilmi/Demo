package com.mihir.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


@Service
public class CustomerService {
    ObjectMapper objectMapper = new ObjectMapper();

    int latestId = 0;


    @PostConstruct
    private void initializer () throws JsonProcessingException {


        try {
            File myObj = new File("C:\\Users\\patil\\Desktop\\practice\\demoData\\count.txt");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            latestId = Integer.valueOf(data);

        }
        catch (IOException e) {
            e.printStackTrace();
        }


        Customer dave = new Customer("1", "Dave", 2011, 10000);
        Customer brenda = new Customer("2", "Brenda", 2018, 50000);

        File daveAndBrenda = new File(getCustomerPath(String.valueOf(latestId)));

        if (!daveAndBrenda.exists()) {
            createCustomerFile(getCustomerPath(String.valueOf(latestId + 1)), dave);
            latestId++;
            createCustomerFile(getCustomerPath(String.valueOf(latestId + 1)), brenda);
            latestId++;
            writeCustomerCount();
        }



    }


    public Customer createCustomer(Customer customer) {
        String id = String.valueOf(latestId + 1);
        customer.setId(id);

        String path = getCustomerPath(id);

        createCustomerFile(path, customer);
        latestId++;
        writeCustomerCount();
        return customer;
    }

    @Cacheable("customers")
    public Customer readCustomer(String id) {

        try {
            Customer customer = objectMapper.readValue(new File(getCustomerPath(id)), Customer.class);
            return customer;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public Collection<Customer> readCustomers() {
//        return customerRepo.values();
//    }

    public Customer updateCustomer(String id, Customer customer) {
        String filepath = getCustomerPath(id);
        File tempFile = new File(filepath);
        if (tempFile.exists()) {
            createCustomerFile(id, customer);
        }

        return null;

    }

    public Customer deleteCustomer(String id) {
        Customer deletedCustomer = readCustomer(id);
        if (deletedCustomer != null) {
            File tempFile = new File(getCustomerPath(id));
            tempFile.delete();
        }
        return deletedCustomer;
    }


    private String getCustomerPath(String id) {
        return "C:\\Users\\patil\\Desktop\\practice\\demoData\\id" + id + ".txt";
    }

    private void createCustomerFile(String filePath, Customer customer) {
        File customerFile = new File(filePath);

        if (!customerFile.exists()) {
            try {
                if (customerFile.createNewFile()) {

                    System.out.println("File created: " + customerFile.getName());
                }
                else {
                    System.out.println("File already exists.");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("An error occurred");
            }
        }



        try {
            FileWriter myWriter = new FileWriter(filePath);
            String customerAsString = objectMapper.writeValueAsString(customer);
            myWriter.write(customerAsString);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred");
        }

    }

    private void writeCustomerCount() {

        String filePath = "C:\\Users\\patil\\Desktop\\practice\\demoData\\count.txt";

        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(String.valueOf(latestId));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred");
        }

    }



}
