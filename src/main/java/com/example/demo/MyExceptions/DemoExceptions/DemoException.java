package com.example.demo.MyExceptions.DemoExceptions;

public class DemoException extends RuntimeException{
    private String message;

    public DemoException(String message){
        super(message);
        this.message = message;
    }
    public DemoException(){

    }
}
