package com.example.demo.dao;

import com.example.demo.model.ProductCart;
import com.example.demo.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartDAO extends JpaRepository<ProductCart, Integer> {
    @Override
    List<ProductCart> findAll();
//    public ProductDAO findByProductAndByQuantity(Products product, int quantity);
//    @Query("delete * from  ProductCart pc where pc.id = :id")
//    public void customDeleteById(int id);
}
