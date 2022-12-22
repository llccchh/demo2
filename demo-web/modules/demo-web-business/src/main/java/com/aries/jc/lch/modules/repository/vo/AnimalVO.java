package com.aries.jc.lch.modules.repository.vo;

/**
 * @author lichanghao6
 */
public class AnimalVO {
    public Integer id;
    public String name;
    public Integer type;
    public String adminName;
    public Integer adminAge;
    public Integer animalVisit;

    @Override
    public String toString() {
        return "AnimalVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", adminName='" + adminName + '\'' +
                ", adminAge=" + adminAge +
                ", animalVisit=" + animalVisit +
                '}';
    }

    public AnimalVO(Integer id, String name, Integer type, Integer animalVisit) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.animalVisit = animalVisit;
    }

    public AnimalVO(Integer id, String name, Integer type, String adminName, Integer adminAge) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.adminName = adminName;
        this.adminAge = adminAge;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getAnimalVisit() {
        return animalVisit;
    }

    public void setAnimalVisit(Integer animalVisit) {
        this.animalVisit = animalVisit;
    }
}
