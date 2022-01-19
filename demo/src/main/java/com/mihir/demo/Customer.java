package com.mihir.demo;

public class Customer {
    //attributes
    String id;
    String name;
    int accountSince;
    int value;

    public Customer(String id, String name, int accountSince, int value) {
        this.id = id;
        this.name = name;
        this.accountSince = accountSince;
        this.value = value;
    }

    public String getId(){ return id; }

    public String getName(){
        return name;
    }

    public int getAccountSince(){
        return accountSince;
    }

    public int getValue(){
        return value;
    }

    public void setId(String identity) { id = identity; }


}
