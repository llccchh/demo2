package com.aries.jc.lch.modules.repository.entity;

import lombok.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lichanghao6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Integer userId;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public LinkedHashMap bean2map(){
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("userId", userId);
        map.put("username", username);
        map.put("password", password);
        return map;
    }

}
