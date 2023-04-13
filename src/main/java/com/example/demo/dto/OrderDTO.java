package com.example.demo.dto;

import com.example.demo.model.address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("address")
    private address shippingAddress;
    @JsonProperty("product_ids")
    private List<Integer> productIds;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }
//    List<Integer> getProductIds()
}
