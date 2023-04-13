package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
@Controller
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer_) {
        System.out.println(customer_);
        return customerService.addCustomer(customer_);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(value = "/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") int customerId) {
        return customerService.getCustomer(customerId);
    }

    @PutMapping(value = "/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer customer_) {
        return customerService.updateCustomer(customerId, customer_);
    }

    @DeleteMapping(value = "/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
    }

    @GetMapping(value = "/phone_no")
    public Customer getCustomerByPhoneNumber(@RequestParam(value = "phone_no") int phoneNo){
        return customerService.getCustomerByPhoneNumber(phoneNo);
    }
//    @DeleteMapping
//    public void deleteAllCustomers(){
//        customerService.deleteAllCustomers();
//    }
//    @GetMapping(value = {})
//    public customer getCustomerByPhoneNumber()
}