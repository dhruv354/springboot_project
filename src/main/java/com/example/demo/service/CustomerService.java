package com.example.demo.service;

//import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;


    public Customer addCustomer(Customer customer_) {
        return customerDAO.save(customer_);
    }

    public List<Customer> getCustomers() {
        return customerDAO.findAll();
    }

    public Customer getCustomer(int customerId) {

        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);

        if(!optionalCustomer.isPresent())
            return null;


        return optionalCustomer.get();
    }

    public Customer updateCustomer(int customerId, Customer customer_) {
        customer_.setId(customerId);
        return customerDAO.save(customer_);
    }

    public void deleteCustomer(int customerId) {
        customerDAO.deleteById(customerId);
    }
    public void deleteAllCustomers(){
        customerDAO.deleteAll();
    }

    public Customer getCustomerByPhoneNumber(int phoneNo){
        System.out.println("hello");
        List<Customer> allCustomers = customerDAO.findAll();
        System.out.println(phoneNo);
        for(int i = 0;i < allCustomers.size();i++){
            Customer tempCustomer = allCustomers.get(i);
            System.out.println(tempCustomer.getPhoneNo());
            if(tempCustomer.getPhoneNo() == phoneNo){
                return tempCustomer;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}