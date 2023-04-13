package com.example.demo.service;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.PrimitiveIterator;

@Service
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    public Products addProduct(Products product){
        return productDAO.save(product);
    }

    public List<Products> getProducts(){
        System.out.println("inside get Product service get mapping");
        return productDAO.findAll();
    }
}
