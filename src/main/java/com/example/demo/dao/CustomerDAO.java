package com.example.demo.dao;


import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerDAO extends CrudRepository<Customer, Integer> {
    @Override
    List<Customer> findAll();
}
