package com.example.demo.MyExceptions.CustomerExceptions;

import com.example.demo.MyExceptions.DemoExceptions.DemoException;
import com.example.demo.model.Customer;

public class CustomerNotFoundException extends DemoException {
    private String message;
    public CustomerNotFoundException(String message){
        super(message);
        this.message = message;
    }
    public CustomerNotFoundException(){

    }
}
