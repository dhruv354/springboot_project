package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import java.util.List;

@Entity
public class Customer {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("phone")
//    @Pattern(regexp="(^$|[0-9]{10})")
    @Column(unique=true)
//    @Constraint(validatedBy = PhoneNumberValidator.class)
    private int phoneNo;
    @JsonProperty("name")
    private String customerName;


    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("address")
    private List < address > Address;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<address> getAddress() {
        return Address;
    }

//    public void setAddress(int CustomerId, ) {
//        Address = address;
//    }
}
