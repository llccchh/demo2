package com.aries.jc.lch.modules.repository.vo;

/**
 * @author lichanghao6
 */
public class AdministratorVO {
    public Integer id;
    public String name;
    public Integer age;

    @Override
    public String toString() {
        return "AdministratorVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public AdministratorVO(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
