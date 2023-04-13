package com.example.demo.dao;

import com.example.demo.model.Orders;
import com.example.demo.model.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends CrudRepository<Products, Integer> {
    @Override
    List<Products> findAll();
}
