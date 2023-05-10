package com.example.demo.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.demo.MyExceptions.CartExceptions.CartAlreadyExistsException;
import com.example.demo.MyExceptions.CustomerExceptions.CustomerAlreadyExistsException;
import com.example.demo.MyExceptions.CustomerExceptions.CustomerNotFoundException;
import com.example.demo.MyExceptions.ProductExceptions.ProductNotFoundException;
import com.example.demo.dto.CartDTO;
import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cart")
@Controller
public class CartResource {
    Logger logger = LoggerFactory.getLogger(CartResource.class);
    @Autowired
    private CartService cartService;



    @PostMapping
    public Cart addProduct(@RequestBody @Valid CartDTO cart) throws CustomerNotFoundException, ProductNotFoundException, CartAlreadyExistsException {
        return cartService.addProduct(cart);
    }
    @GetMapping(value = "/{user_id}")
    public Cart getCart(@PathVariable("user_id") int userId) throws Exception{
        return cartService.getCart(userId);
    }

    @PutMapping(value = "/{cart_id}")
    public Cart updateCart(@PathVariable("cart_id") int cartId, @RequestParam(value = "quantity") int quantity, @RequestParam(value = "product_id") int product_id) throws Exception {
        logger.info("inside cart put request");
        return cartService.updateCart(cartId, quantity, product_id);
    }
    @DeleteMapping(value = "/{cart_id}")
        public void deleteCart(@PathVariable("cart_id") int cart_id) throws Exception{
            cartService.deleteCart(cart_id);

    }
}
