package com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

/**
 * @author lichanghao6
 */
@Data
@TableName("xp_link_group")
public class LinkGroup {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String groupName;
    private String description;
    private String createUser;
    private Date createTime;
    private Date updateTime;
    /**
     * 数据逻辑删除，默认为 0，删除为 -1
     */
    @TableLogic(value = "0", delval = "-1")
    private Integer dataState;

    public LinkGroup(Integer id, String groupName, String description, String createUser, Date createTime, Date updateTime, Integer dataState) {
        this.id = id;
        this.groupName = groupName;
        this.description = description;
        this.createUser = createUser;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.dataState = dataState;
    }

    public LinkGroup() {
    }
}


