package com.example.demo.dao;

import com.example.demo.model.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDAO extends CrudRepository<Orders, Integer> {
    @Override
    List<Orders> findAll();
}
