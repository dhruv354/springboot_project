package com.example.demo.dao;
import com.example.demo.dto.CartDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartDAO extends CrudRepository<Cart, Integer> {
    @Override
    List<Cart> findAll();

//    public Cart findByCustomerAndProduct(Customer customer, Products product);
    public Cart findByCustomer(Customer customer);
//    public Cart findByCustomerAndByProduct(Customer customer, Product)
}
