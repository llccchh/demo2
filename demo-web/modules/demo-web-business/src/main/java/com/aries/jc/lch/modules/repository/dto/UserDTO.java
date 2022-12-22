package com.aries.jc.lch.modules.repository.dto;

import lombok.Data;

public class UserDTO {

    private String userName;

    public UserDTO(String userName) {
        this.userName = userName;
    }

    public UserDTO() {
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
