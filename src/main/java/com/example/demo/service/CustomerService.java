package com.example.demo.service;

//import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.controller.CustomerResource;
import com.example.demo.model.Customer;
import com.example.demo.dao.CustomerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerDAO customerDAO;


    public Customer addCustomer(Customer customer) throws Exception {
//        Phone Number validation
        String patterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(Long.toString(customer.getPhoneNo()));
        if(matcher.matches()){
            return customerDAO.save(customer);
        }
        throw new Exception("this phone number id invalid");
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

    public Customer getCustomerByPhoneNumber(Long phoneNo){
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