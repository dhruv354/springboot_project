package com.example.demo.model;

import com.example.demo.dto.CoordinateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class Address {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    @NotNull
//    @NotBlank
    @JsonProperty("house")
    private int houseNo;

    @NotNull
    @JsonProperty("area")
//    @NotNull
    private String area;
    @JsonProperty("city")
    @NotNull
//    @NotNull
    private String city = "";
    @JsonProperty("state")
    @NotNull
//    @NotNull
    private String state = "";

//    @JsonProperty("coordinates")
//    private CoordinateDTO coordinates;
//
//    public CoordinateDTO getCoordinates() {
//        return coordinates;
//    }
//
//    public void setCoordinates(CoordinateDTO coordinates) {
//        this.coordinates = coordinates;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

//    public String getSity() {
//        return sity;
//    }

//    public void setSity(String sity) {
//        this.sity = sity;
//    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
