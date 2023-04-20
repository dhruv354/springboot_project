package com.example.demo.dao;

import com.example.demo.model.ProductCart;
import com.example.demo.model.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartDAO extends CrudRepository<ProductCart, Integer> {
    @Override
    List<ProductCart> findAll();
//    public ProductDAO findByProductAndByQuantity(Products product, int quantity);
}
