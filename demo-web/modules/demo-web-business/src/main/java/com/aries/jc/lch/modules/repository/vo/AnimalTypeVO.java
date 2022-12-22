package com.aries.jc.lch.modules.repository.vo;

/**
 * @author lichanghao6
 */
public class AnimalTypeVO {
    public Integer id;
    public String typeName;
    public String hobby;
    public String name;
    public String adminName;
    public Integer adminAge;

    @Override
    public String toString() {
        return "AnimalTypeVO{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", hobby='" + hobby + '\'' +
                ", name='" + name + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminAge=" + adminAge +
                '}';
    }

    public AnimalTypeVO(Integer id, String typeName, String hobby, String name, String adminName, Integer adminAge) {
        this.id = id;
        this.typeName = typeName;
        this.hobby = hobby;
        this.name = name;
        this.adminName = adminName;
        this.adminAge = adminAge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getAdminAge() {
        return adminAge;
    }

    public void setAdminAge(Integer adminAge) {
        this.adminAge = adminAge;
    }
}
