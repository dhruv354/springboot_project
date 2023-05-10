package com.example.demo.MyExceptions.CartExceptions;

import com.example.demo.MyExceptions.CustomerExceptions.CustomerNotFoundException;
import com.example.demo.MyExceptions.DemoExceptions.DemoException;
import com.example.demo.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartAlreadyExistsException extends DemoException {
    Logger logger = LoggerFactory.getLogger(CartAlreadyExistsException.class);
    private String message;

    public CartAlreadyExistsException(String message){
        super(message);
        this.message = message;
        logger.error("Error in Customer class, user already exists with this user id");
    }
    public CartAlreadyExistsException(){

    }
}
