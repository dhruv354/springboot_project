package com.example.demo.service;

//import com.example.demo.exception.CustomerNotFoundException;
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
//import sun.jvm.hotspot.debugger.Address;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerDAO customerDAO;


    public Customer addCustomer(Customer customer)  {
//        Phone Number validation
//        String patterns
//                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
//                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
//                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
//        Pattern pattern = Pattern.compile(patterns);
//        Matcher matcher = pattern.matcher(Long.toString(customer.getPhoneNo()));
//        if (matcher.matches()) {
            return customerDAO.save(customer);
//        }
//        throw new Exception("this phone number id invalid");
    }

    public List<Customer> getCustomers() {
        return customerDAO.findAll();
    }

    public Customer getCustomer(int customerId) {

        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);

        if (!optionalCustomer.isPresent())
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

    public void deleteAllCustomers() {
        customerDAO.deleteAll();
    }

    public Customer getCustomerByPhoneNumber(String phoneNo) {
        System.out.println("hello");
        List<Customer> allCustomers = customerDAO.findAll();
        System.out.println(phoneNo);
        for (int i = 0; i < allCustomers.size(); i++) {
            Customer tempCustomer = allCustomers.get(i);
            System.out.println(tempCustomer.getPhone());
            if (tempCustomer.getPhone() == phoneNo) {
                return tempCustomer;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
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
            currentCustomer = body.getResponseListUserDTO().get(i).convert

            customerList.add(currentCustomer);
        }
        customerDAO.saveAll(customerList);
        return customerList;

//        JSONObject jsonObject = new JSONObject(body);
//
//        JSONArray users = (JSONArray)jsonObject.get("users");
//        for(int i = 0;i < users.length();i++){
//            JSONObject user = (JSONObject) users.get(i);
//            System.out.println(user);
//            Customer customer = new Customer();
//            try {
//                if(user.has("firstName") && user.has("lastName"))
//                    customer.setCustomerName(user.get("firstName") + " " + user.get("lastName"));
//                if(user.has("phone"))
//                    customer.setPhoneNo((String)user.get("phone"));
//                Address add = new Address();
//                if(user.has("address")) {
//                    JSONObject userAddress = (JSONObject) user.get("address");
//                    if(userAddress.has("address"))
//                        add.setArea((String) userAddress.get("address"));
//                    if(userAddress.has("city"))
//                        add.setCity((String) userAddress.get("city"));
//                    if(userAddress.has("state"))
//                        add.setState((String) userAddress.get("state"));
//                    if(user.has("postalCode"))
//                        add.setPinCode(Integer.parseInt((String) userAddress.get("postalCode")));
//                }
//                List<Address> tmp = new ArrayList<>();
//                tmp.add(add);
//                customer.setAddress(tmp);
//                customerDAO.save(customer);
//            }
//            catch(Exception e){
//                System.out.println(e);
//                throw e;
//            }
//        }
//        return null;
    }

    public List<Customer> getCustomerUsingLoginName(String username){
        List<Customer> output = customerDAO.findByUsername(username);
        return output;
    }

    public List<Customer> findCustomerLikeUsername(String username){
        return customerDAO.findUsingUserName(username);
    }
}