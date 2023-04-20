package com.example.demo.controller;

import com.example.demo.constants.OrderAmountByYear;
import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.Orders;
import com.example.demo.model.Products;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @PostMapping
    public Orders addOrder(@RequestBody OrderDTO order) throws Exception {
        System.out.println(order);
        List<Integer> products = order.getProductIds();
        int userId = order.getUserId();
//        for(int i = 0;i < products.size();i++){
//            Optional<Cart> optionalCart = new Cart();
//            for
//        }
        return orderService.placeOrder(order);
    }

    @GetMapping
    public List<Orders> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping(value = "/{year}")
    public List<OrderAmountByYear> getOrderAmountByYear(@PathVariable("year") int year){
        System.out.println(year);
        return orderService.getOrderAmountByYear(year);
    }
}




