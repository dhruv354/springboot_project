package com.example.demo.service;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Products;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.PrimitiveIterator;

@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductDAO productDAO;

    public Products addProduct(Products product){
        return productDAO.save(product);
    }

    public List<Products> getProducts(){
        logger.info("inside get Product service get mapping");
        return productDAO.findAll();
    }
}
