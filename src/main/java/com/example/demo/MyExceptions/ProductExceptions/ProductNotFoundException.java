package com.example.demo.MyExceptions.ProductExceptions;

import com.example.demo.MyExceptions.DemoExceptions.DemoException;
import com.example.demo.model.Products;

public class ProductNotFoundException extends DemoException{
    private String message;

    public ProductNotFoundException(String message){
        super(message);
        this.message = message;
    }

    public ProductNotFoundException(){

    }
}
