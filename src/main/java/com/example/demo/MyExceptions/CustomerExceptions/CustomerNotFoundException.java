package com.example.demo.MyExceptions.CustomerExceptions;

import com.example.demo.MyExceptions.DemoExceptions.DemoException;
import com.example.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerNotFoundException extends DemoException {

    Logger logger = LoggerFactory.getLogger(CustomerNotFoundException.class);
    private String message;
    public CustomerNotFoundException(String message){
        super(message);
        this.message = message;
        logger.error("Error in Customer class, it is giving customer Not found exception");
    }
    public CustomerNotFoundException(){

    }
}
