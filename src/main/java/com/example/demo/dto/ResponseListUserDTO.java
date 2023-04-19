package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseListUserDTO {

    @JsonProperty("users")
    private List<UserDTO> responseListUserDTO;

    public List<UserDTO> getResponseListUserDTO() {
        return responseListUserDTO;
    }

    public void setResponseListUserDTO(List<UserDTO> responseListUserDTO) {
        this.responseListUserDTO = responseListUserDTO;
    }
}
