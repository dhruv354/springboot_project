package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Cart {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne (cascade =  CascadeType.PERSIST)

    private  Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<ProductCart> product;

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

    public List<ProductCart> getProduct() {
        return product;
    }

    public void setProduct(List<ProductCart> product) {
        this.product = product;
    }
}
