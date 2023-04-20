package com.example.demo.dto;

import com.example.demo.model.Address;

import javax.validation.constraints.Min;

public class UserDTO {
    private String firstName;
    private String lastName;

    private HairDTO hair;

    private String gender;

    @Min(0)
    private int age;

    private String phone;

    private Address address;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public HairDTO getHair() {
        return hair;
    }

    public void setHair(HairDTO hair) {
        this.hair = hair;
    }

    public String getGender() {
        return gender;
    }



    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
