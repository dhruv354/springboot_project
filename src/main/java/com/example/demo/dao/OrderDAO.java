package com.example.demo.dao;

import com.example.demo.model.Customer;
import com.example.demo.model.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface OrderDAO extends JpaRepository<Orders, Integer> {

    public List<Orders> findByCustomer(Customer customer);
    public List<Orders> findByCustomerOrderByOrderDateDesc(Customer customer, Pageable paging);
}
