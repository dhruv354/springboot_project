package com.example.demo.MyExceptions.ProductExceptions;

import com.example.demo.MyExceptions.DemoExceptions.DemoException;
import com.example.demo.model.Products;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductNotFoundException extends DemoException{

    Logger logger = LoggerFactory.getLogger(ProductNotFoundException.class);
    private String message;

    public ProductNotFoundException(String message){
        super(message);
        this.message = message;
        logger.error("Error inside Product class, it is giving Product Not found exception");
    }

    public ProductNotFoundException(){

    }
}
