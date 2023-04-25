package com.example.demo.service;

//import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.MyExceptions.CustomerExceptions.CustomerAlreadyExistsException;
import com.example.demo.MyExceptions.CustomerExceptions.CustomerNotFoundException;
import com.example.demo.dto.ResponseListUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Customer;
import com.example.demo.dao.CustomerDAO;
import com.example.demo.model.Address;
import org.apache.catalina.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerDAO customerDAO;


    public Customer addCustomer(Customer customer)  throws CustomerAlreadyExistsException{
        Customer optionalCustomer = customerDAO.findByUsername(customer.getUsername());
        if(optionalCustomer != null){
            throw new CustomerAlreadyExistsException("Customer already exists with this username, try a different username");
        }
        return customerDAO.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerDAO.findAll();
    }

    public Customer getCustomer(int customerId) throws CustomerNotFoundException{

        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);

        if (!optionalCustomer.isPresent())
            throw new CustomerNotFoundException("customer with id does not exist");


        return optionalCustomer.get();
    }

    public Customer updateCustomer(int customerId, Customer customer_) {
        customer_.setId(customerId);
        return customerDAO.save(customer_);
    }

    public void deleteCustomer(int customerId) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);
        if(!optionalCustomer.isPresent()){
            throw new CustomerNotFoundException("user with id: " + customerId + " does not exist");
        }
        customerDAO.deleteById(customerId);
    }

    public void deleteAllCustomers() {
        customerDAO.deleteAll();
    }

    public Customer getCustomerByPhoneNumber(String phoneNo) throws CustomerNotFoundException{
        List<Customer> allCustomers = customerDAO.findAll();
        System.out.println(phoneNo);
        for (int i = 0; i < allCustomers.size(); i++) {
            Customer tempCustomer = allCustomers.get(i);
            if (tempCustomer.getPhone() == phoneNo) {
                return tempCustomer;
            }
        }
        throw new CustomerNotFoundException("customer with this number " + phoneNo + " does not exist");
    }

    public List<Customer> getGithubCustomers() {
        RestTemplate restTemplate = new RestTemplate();

        String url
                = "https://dummyjson.com/users";

        // Fetch JSON response as String wrapped in ResponseEntity
        ResponseEntity<ResponseListUserDTO> response =
                restTemplate.getForEntity(url, ResponseListUserDTO.class);
        ResponseListUserDTO body = response.getBody();
        System.out.println(body);
        System.out.println(body.getResponseListUserDTO());

        List<Customer> customerList = new ArrayList<>();

        for(int i = 0;i < body.getResponseListUserDTO().size();i++){
            Customer currentCustomer = new Customer();
            UserDTO givenCustomer = body.getResponseListUserDTO().get(i);
            currentCustomer.setFirstName(givenCustomer.getFirstName());
            currentCustomer.setLastName(givenCustomer.getLastName());
            currentCustomer.setAge(givenCustomer.getAge());
            currentCustomer.setGender(givenCustomer.getGender());
            currentCustomer.setPhone(givenCustomer.getPhone());
            currentCustomer.setUsername(givenCustomer.getUsername());
            currentCustomer.setPassword(givenCustomer.getPassword());
            List<Address> address = new ArrayList<>();
            address.add(givenCustomer.getAddress());
            currentCustomer.setAddress(address);
            customerList.add(currentCustomer);
        }
        customerDAO.saveAll(customerList);
        return customerList;
    }

    public Customer getCustomerUsingLoginName(String username){
        Customer customer = customerDAO.findByUsername(username);
        return customer;
    }

    public List<Customer> findCustomerLikeUsername(String username) throws CustomerNotFoundException{
        try {
            return customerDAO.findUsingUserName(username);
        }
        catch(CustomerNotFoundException customerNotFoundException){
            throw new CustomerNotFoundException("customer with username: " + username + " does not exist");
        }
    }
}