package com.example.demo.service;

import com.example.demo.MyExceptions.CustomerExceptions.CustomerNotFoundException;
import com.example.demo.MyExceptions.ProductExceptions.ProductNotFoundException;
import com.example.demo.constants.OrderAmountByYear;
import com.example.demo.dao.*;
import com.example.demo.dto.OrderDTO;
import com.example.demo.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class OrderService {

    Logger logger =  LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private ProductCartDAO productCartDAO;

    public Orders addOrder(Orders order) {

        return orderDAO.save(order);
    }

    public Orders placeOrder(OrderDTO order) throws Exception, CustomerNotFoundException, ProductNotFoundException {

        Orders placedOrder = new Orders();
        int userId = order.getUserId();
        List<Integer> productIds = order.getProductIds();

        //Check if user exists else throw exception
        Optional<Customer> optionalCustomer = customerDAO.findById(userId);
        if(!optionalCustomer.isPresent()) {
            throw new CustomerNotFoundException("customer with id does not exist, " + userId);
        }
        Customer customer = optionalCustomer.get();
        //check all Product Id
        List<Products>validProducts = new ArrayList<>();
        int orderAmount = 0;
        for(int i = 0;i < productIds.size();i++){
            Optional<Products> optionalProduct = productDAO.findById(productIds.get(i));

            if(!optionalProduct.isPresent())
                throw new ProductNotFoundException("product with this does id does not exist");
            Products product = optionalProduct.get();
            validProducts.add(product);
            orderAmount += product.getPrice();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String curTime = dtf.format(now);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try {
            date = formatter.parse(curTime);
        }
        catch(Exception e){
            logger.info(e.getMessage());
        }
        placedOrder.setOrderAmount(orderAmount);
        placedOrder.setCustomer(customer);
        placedOrder.setProducts(validProducts);
        placedOrder.setShippingAddress(order.getShippingAddress());
        placedOrder.setOrderDate(date);

        //remove product from the cart
        Cart cart = cartDAO.findByCustomer(optionalCustomer.get());
        if(cart == null){
            return orderDAO.save(placedOrder);
        }
        List<ProductCart> productCart = cart.getProduct();
        HashMap<Integer, Boolean> mp = new HashMap<Integer,Boolean>();
        for(int i = 0;i < order.getProductIds().size();i++){
            mp.put(order.getProductIds().get(i), true);
        }
        List<ProductCart> newProductCartArr = new ArrayList<>();
        for(int i = 0;i < productCart.size();i++){
            Products product = productCart.get(i).getProduct();
            if(!mp.containsKey(product.getId())) {
                throw new Exception("all products mentioned in the cart are not being ordered");
            }
        }
        cartDAO.delete(cart);
        return orderDAO.save(placedOrder);
    }

    public List<Orders> getOrders(int user_id) throws Exception {
        Optional<Customer> customer = customerDAO.findById(user_id);
        if(!customer.isPresent()){
            throw new CustomerNotFoundException("customer with id does not exist");
        }
        List<Orders> orders = orderDAO.findByCustomer(customer.get());
        return orders;
    }

    public List<Orders> getOrders2(int user_id, Pageable paging) throws CustomerNotFoundException{
        Optional<Customer> customer = customerDAO.findById(user_id);
        if(!customer.isPresent()){
            throw new CustomerNotFoundException("customer with this id does not exist");
        }
        List<Orders> orders = orderDAO.findByCustomerOrderByOrderDateDesc(customer.get(), paging);
        return orders;
    }

    public List<OrderAmountByYear> getOrderAmountByYear(int givenYear){
        Dictionary<Integer, Integer> dict= new Hashtable<>();
        List<Orders> orders = orderDAO.findAll();
        for(int i = 0;i < orders.size();i++){
            Orders currentOrder = orders.get(i);
            int year = currentOrder.getOrderDate().getYear() + 1900;
            int month = currentOrder.getOrderDate().getMonth() + 1;
            int orderAmount = currentOrder.getOrderAmount();
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
             OrderAmountByYear current = new OrderAmountByYear();
             current.setOrderAmount(key_value);
             current.setMonth(key);
             output.add(current);
        }
        System.out.println(output.size());
        return output;
    }
}
