package com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author lichanghao6
 */
@Data
@TableName("xp_link_action")
public class LinkAction {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String actionName;
    private String linkChannelName;
    private String linkChannelCode;
    private String actionParam;
    private Integer armingPlanId;
    private Integer executeSequence;
    private Timestamp createTime;
    /**
     * 数据逻辑删除，默认为 0，删除为 -1
     */
    @TableLogic(value = "0", delval = "-1")
    private Integer dataState;

    public LinkAction(Integer id, String actionName, String linkChannelName, String linkChannelCode, String actionParam,
                      Integer armingPlanId, Integer executeSequence, Timestamp createTime, Integer dataState) {
        this.id = id;
        this.actionName = actionName;
        this.linkChannelName = linkChannelName;
        this.linkChannelCode = linkChannelCode;
        this.actionParam = actionParam;
        this.armingPlanId = armingPlanId;
        this.executeSequence = executeSequence;
        this.createTime = createTime;
        this.dataState = dataState;
    }

    public LinkAction() {
    }
}
