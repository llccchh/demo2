package com.aries.jc.lch.modules.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lichanghao6
 * 员工表
 */
@Data
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String lastName;
    private  String email;
    private Integer gender;
    private Department department;
    private Date birth;

    /**
     * 将有参构造中的Date参数改为内部生成
     */
    public Employee(Integer id, String lastName, String email, Integer gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        //默认的创建日期
        this.birth = new Date();
    }
}
