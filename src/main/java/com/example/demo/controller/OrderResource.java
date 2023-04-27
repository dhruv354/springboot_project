package com.example.demo.controller;

import com.example.demo.MyExceptions.CustomerExceptions.CustomerNotFoundException;
import com.example.demo.MyExceptions.ProductExceptions.ProductNotFoundException;
import com.example.demo.constants.OrderAmountByYear;
import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Orders;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import org.json.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addOrder(@RequestBody OrderDTO order) throws Exception, CustomerNotFoundException, ProductNotFoundException {
        System.out.println(order);
        List<Integer> products = order.getProductIds();
        try{
            return new ResponseEntity(orderService.placeOrder(order), HttpStatus.OK);
        }
        catch(CustomerNotFoundException c){
            return new ResponseEntity<>(c.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch(ProductNotFoundException p){
            return new ResponseEntity(p.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //sorting by orders and pagination
    @GetMapping
    public List<Orders> getOrders(@RequestParam(name = "user_id") int user_id) throws Exception {
        return orderService.getOrders(user_id);
    }

    @GetMapping(value = "/sorted_orders")
    public ResponseEntity getOrders2(
            @RequestParam(name = "user_id") int user_id,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    ) throws Exception {
        logger.info("inside get orders class");
        try {
            Pageable pagingSort = PageRequest.of(page, size);
            return new ResponseEntity(orderService.getOrders2(user_id, pagingSort), HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{year}")
    public List<OrderAmountByYear> getOrderAmountByYear(@PathVariable("year") int year){
        return orderService.getOrderAmountByYear(year);
    }
}




