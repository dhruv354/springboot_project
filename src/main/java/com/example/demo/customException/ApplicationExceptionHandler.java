package com.example.demo.customException;

import com.example.demo.MyExceptions.CustomerExceptions.CustomerAlreadyExistsException;
import com.example.demo.MyExceptions.CustomerExceptions.CustomerNotFoundException;
import com.example.demo.MyExceptions.ProductExceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> invalidArguement(MethodArgumentNotValidException e){
        Map<String, String> errorMap= new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public String customerNotFound(CustomerNotFoundException c){
        return c.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String productNotFound(ProductNotFoundException p){
        return p.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public String customerAlreadyExists(CustomerAlreadyExistsException c){
        return c.getMessage();
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String generalExceptionHandler(Exception e){
        return e.getMessage();
    }
}
