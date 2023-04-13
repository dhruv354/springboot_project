package com.example.demo.controller;

import com.example.demo.constants.OrderAmountByYear;
import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Orders;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Orders addOrder(@RequestBody OrderDTO order) throws Exception {
        System.out.println(order);
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




