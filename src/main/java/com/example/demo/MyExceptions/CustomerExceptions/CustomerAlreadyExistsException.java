package com.example.demo.MyExceptions.CustomerExceptions;

import com.example.demo.MyExceptions.DemoExceptions.DemoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerAlreadyExistsException extends DemoException {

    Logger logger = LoggerFactory.getLogger(CustomerNotFoundException.class);
    private String message;

    public CustomerAlreadyExistsException(String message){
        super(message);
        this.message = message;
        logger.error("Error in Customer class, user already exists with this user id");
    }
    public CustomerAlreadyExistsException(){

    }
}
