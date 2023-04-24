package com.example.demo.controller;

import com.example.demo.MyExceptions.CustomerExceptions.CustomerAlreadyExistsException;
import com.example.demo.MyExceptions.CustomerExceptions.CustomerNotFoundException;
import com.example.demo.dao.CustomerDAO;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customers")
@Controller
public class CustomerResource {

        Logger logger = LoggerFactory.getLogger(CustomerResource.class);
        @Autowired
        private CustomerService customerService;
    @Autowired
    private CustomerDAO customerDAO;

    @PostMapping
        public ResponseEntity addCustomer(@RequestBody @Valid  Customer customer)  throws CustomerAlreadyExistsException {
            logger.info("adding customers");
            try{
                return new ResponseEntity(customerService.addCustomer(customer), HttpStatus.CREATED);
            }
            catch (CustomerAlreadyExistsException e){
                return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
            }
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
        private ResponseEntity getCustomerUsingLoginName(@PathVariable("login_name") String loginName) throws CustomerNotFoundException{
            try{
                return new ResponseEntity(customerService.findCustomerLikeUsername(loginName), HttpStatus.OK);
            }
            catch(CustomerNotFoundException customerNotFoundException){
                return new ResponseEntity(customerNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        }



        @GetMapping(value = "/{customerId}")
        public ResponseEntity getCustomer(@PathVariable("customerId") int customerId) throws CustomerNotFoundException{
            try{
                return new ResponseEntity(customerService.getCustomer(customerId), HttpStatus.OK);
            }
            catch (CustomerNotFoundException customerNotFoundException){
                return new ResponseEntity(customerNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping(value = "/{customerId}")
        public Customer updateCustomer(@PathVariable("customerId") int customerId, @RequestBody Customer customer_) {
            return customerService.updateCustomer(customerId, customer_);
        }

        @DeleteMapping(value = "/{customerId}")
        public ResponseEntity deleteCustomer(@PathVariable("customerId") int customerId) throws CustomerNotFoundException{
            try{
                customerService.deleteCustomer(customerId);
                return new ResponseEntity(HttpStatus.OK);
            }
            catch (CustomerNotFoundException customerNotFoundException){
                return new ResponseEntity<>(customerNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping(value = "/phone_no")
        public Customer getCustomerByPhoneNumber(@RequestParam(value = "phone_no") String phoneNo) throws CustomerNotFoundException{
            return customerService.getCustomerByPhoneNumber(phoneNo);
        }

}