package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
@Controller
public class CustomerResource {

        Logger logger = LoggerFactory.getLogger(CustomerResource.class);
        @Autowired
        private CustomerService customerService;

        @PostMapping
        public Customer addCustomer(@RequestBody @Valid  Customer customer_)  {
            logger.info("adding customers");
            return customerService.addCustomer(customer_);
        }

        @GetMapping
        public List<Customer> getCustomers() {
            return customerService.getCustomers();
        }

        @GetMapping (value = "/github_users")
        private List<Customer> saveGithubCustomers(){
            return customerService.getGithubCustomers();
        }

        @GetMapping(value = "/username/{login_name}")
        private List<Customer> getCustomerUsingLoginName(@PathVariable("login_name") String loginName){
            return customerService.findCustomerLikeUsername(loginName);
        }



        @GetMapping(value = "/{customerId}")
        public Customer getCustomer(@PathVariable("customerId") int customerId) {
            logger.info("getting a customer through customer id");
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
        public Customer getCustomerByPhoneNumber(@RequestParam(value = "phone_no") String phoneNo){
            return customerService.getCustomerByPhoneNumber(phoneNo);
        }

}