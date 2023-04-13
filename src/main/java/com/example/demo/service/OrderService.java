package com.example.demo.service;

import com.example.demo.constants.OrderAmountByYear;
import com.example.demo.dao.CustomerDAO;
import com.example.demo.dao.ProductDAO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Customer;
import com.example.demo.constants.OrderAmountByYear;
import com.example.demo.model.Orders;
import com.example.demo.dao.OrderDAO;
import com.example.demo.model.Products;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;


@Service
public class OrderService {

//    Logger logger = (Logger) LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ProductDAO productDAO;

    public Orders addOrder(Orders order) {

        return orderDAO.save(order);
    }

    public Orders placeOrder(OrderDTO order) throws Exception {
        Orders placedOrder = new Orders();
        int userId = order.getUserId();
        List<Integer> productIds = order.getProductIds();

        //Check if user exists else throw exception
        Optional<Customer> optionalCustomer = customerDAO.findById(userId);
        System.out.println(optionalCustomer);
        if(!optionalCustomer.isPresent()) {
            System.out.println("here1");
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "User with this id does not exist");
            throw new Exception("User with this id does not exist");
        }
        Customer customer = optionalCustomer.get();
        //check all Product Id
        List<Products>validProducts = new ArrayList<>();
        int orderAmount = 0;
        System.out.println("here2");
        for(int i = 0;i < productIds.size();i++){
            Optional<Products> optionalProduct = productDAO.findById(productIds.get(i));

            if(!optionalProduct.isPresent())
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "product with id " +   productIds.get(i) + "does not exist");
            Products product = optionalProduct.get();
            validProducts.add(product);
            orderAmount += product.getPrice();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String curTime = dtf.format(now);
        System.out.println(curTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try {
            date = formatter.parse(curTime);
        }
        catch(Exception e){
            System.out.println(e);
        }
//        logger.info("An INFO Message: " + date.toString());
        System.out.println("curyear: " + date.getYear());
        System.out.println("cur month: " + date.getMonth());
        placedOrder.setOrderAmount(orderAmount);
        placedOrder.setCustomer(customer);
        placedOrder.setProducts(validProducts);
        placedOrder.setAddress(placedOrder.getAddress());
        placedOrder.setOrderDate(date);
        return orderDAO.save(placedOrder);
    }

    public List<Orders> getOrders(){
        return orderDAO.findAll();
    }

    public List<OrderAmountByYear> getOrderAmountByYear(int givenYear){
        Dictionary<Integer, Integer> dict= new Hashtable<>();
        List<Orders> orders = orderDAO.findAll();
        System.out.println(orders.size());
        for(int i = 0;i < orders.size();i++){
            Orders currentOrder = orders.get(i);
            int year = currentOrder.getOrderDate().getYear() + 1900;
            int month = currentOrder.getOrderDate().getMonth() + 1;
            int orderAmount = currentOrder.getOrderAmount();
            System.out.println("year: " + year );
            System.out.println("month: " + month);
            if(year == givenYear){
                int prevVal = 0;
                if(dict.get(month) == null){
                    prevVal = 0;
                }
                else{
                    prevVal = dict.get(month);
                }
                dict.get(month);
                dict.put(month, prevVal + orderAmount);
            }
        }
        List<OrderAmountByYear> output = new ArrayList<>();
        Enumeration<Integer> keys = dict.keys();

        while (keys.hasMoreElements()) {
             int key = keys.nextElement();
             int key_value = dict.get(key);
             System.out.println(key);
             System.out.println(key_value);
             OrderAmountByYear current = new OrderAmountByYear();
             current.setOrderAmount(key_value);
             current.setMonth(key);
             output.add(current);
        }
        System.out.println(output.size());
        return output;
    }
}