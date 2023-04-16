package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull
//    @NotBlank
    @Column(unique=true)
//    @Constraint(validatedBy = PhoneNumberValidator.class)
    private Long phoneNo;
    @JsonProperty("name")
    @NotNull
    @NotBlank
    private String customerName;


    @OneToMany(cascade = CascadeType.ALL)
    @JsonProperty("address")
    @NotNull
//    @NotBlank
    private List < address > Address;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
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
