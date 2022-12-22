package com.aries.jc.lch.modules.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author lichanghao6
 */

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {



    @Value("1")
    private Integer id;
    @Value("tttttt")
    private String name;
    @Value("1")
    private Integer gender;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
