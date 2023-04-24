package com.example.demo.MyExceptions.DemoExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoException extends RuntimeException{
    Logger logger = LoggerFactory.getLogger(DemoException.class);
    private String message;
    public DemoException(String message){
        super(message);
        this.message = message;
        logger.error("Error inside Demo Class");
    }
    public DemoException(){

    }
}
