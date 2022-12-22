package com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author liting43
 * @date 2022/11/14 16:45
 * @description
 */
@Data
@TableName(value = "xp_link_r_action_ability")
public class ReActionAbility {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String actionName;
    private String capability;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public String getActionName() {
        return actionName;
    }

    public String getCapability() {
        return capability;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
