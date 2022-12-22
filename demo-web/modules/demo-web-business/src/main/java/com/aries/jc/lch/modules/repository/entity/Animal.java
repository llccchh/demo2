package com.aries.jc.lch.modules.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author lichanghao6
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animal")
public class Animal {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id")
    /**
     * @TableId注解 id 主键自增,使用上面两个注解和此注解效果一致
     */
    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "type")
    public Integer type;

    @Column(name = "admin_id")
    public Integer adminId;

    @Column(name = "visit_num")
    public Integer visitNum;

//    public Animal() {
//    }
//
//    public Animal(Integer id, String name, Integer type, Integer adminId, Integer visitNum) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//        this.adminId = adminId;
//        this.visitNum = visitNum;
//    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getType() {
//        return type;
//    }
//
//    public void setType(Integer type) {
//        this.type = type;
//    }
//
//    public Integer getAdminId() {
//        return adminId;
//    }
//
//    public void setAdminId(Integer adminId) {
//        this.adminId = adminId;
//    }
//
//    public Integer getVisitNum() {
//        return visitNum;
//    }
//
//    public void setVisitNum(Integer visitNum) {
//        this.visitNum = visitNum;
//    }
}
