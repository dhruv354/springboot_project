package com.example.demo.controller;

import com.example.demo.constants.OrderAmountByYear;
import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.Orders;
import com.example.demo.model.Products;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
    Logger logger = LoggerFactory.getLogger(OrderResource.class);

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

    //sorting by orders and pagination
    @GetMapping
    public List<Orders> getOrders(@RequestParam(name = "user_id") int user_id) throws Exception {
        return orderService.getOrders(user_id);
    }

    @GetMapping(value = "/sorted_orders")
    public List<Orders> getOrders2(
            @RequestParam(name = "user_id") int user_id,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ) throws Exception {
        logger.info("inside get orders class");
        try {
            Pageable pagingSort = PageRequest.of(page, size);
            return orderService.getOrders2(user_id, pagingSort);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping(value = "/{year}")
    public List<OrderAmountByYear> getOrderAmountByYear(@PathVariable("year") int year){
        System.out.println(year);
        return orderService.getOrderAmountByYear(year);
    }
}




