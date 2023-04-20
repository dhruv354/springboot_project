package com.example.demo.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerDAO extends CrudRepository<Customer, Integer> {
    @Override
    List<Customer> findAll();

    public List<Customer> findByUsername(String username);

    @Query("from Customer c where c.username like ?1%")
    public List<Customer> findUsingUserName(String name);
}
