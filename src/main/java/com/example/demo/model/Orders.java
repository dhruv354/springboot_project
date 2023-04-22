package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;



@Entity
public class Orders {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    @JsonProperty("user_id")
    @ManyToOne(cascade = CascadeType.ALL)
//    @NotNull
//    @NotBlank
    private Customer customer;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy/MM/dd")
//    @NotNull
//    @NotBlank
    private Date orderDate;

    @JsonProperty("order_amount")
//    @NotNull
//    @NotBlank
    private int orderAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty("shipping_address")
//    @NotNull
//    @NotBlank
    private Address shippingAddress;





    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonProperty("products")
//    @NotNull
//    @NotBlank
    private List<Products> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
