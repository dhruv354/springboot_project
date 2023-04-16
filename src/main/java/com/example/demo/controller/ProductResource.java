package com.example.demo.controller;

import com.example.demo.model.Products;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Products addProduct(@RequestBody @Valid Products product){
        return productService.addProduct(product);
    }
    @GetMapping
    public List<Products> getProducts() {
        System.out.println("inside get Products");
        return productService.getProducts();
    }
}

