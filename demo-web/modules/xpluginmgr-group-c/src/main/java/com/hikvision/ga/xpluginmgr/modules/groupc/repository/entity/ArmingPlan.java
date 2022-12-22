package com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liting43
 * @date 2022/11/4 11:05
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "xp_link_arming_plan")
public class ArmingPlan {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String armingPlanName;
    private Integer groupId;
    private String startChannelName;
    private String startChannelCode;
    private Integer eventId;
    /**
     * 事件等级：1-低，2-中，3-高
     */
    private Integer eventLevel;
    private Integer timeTempId;
    /**
     * 布撤防状态：0-未启用、禁用，1-启用，默认为 0
     */
    private Integer armingPlanStatus;
    private String description;
    private String createUser;
    private Date createTime;
    private Date updateTime;
    /**
     * 数据逻辑删除，默认为 0，删除为 -1
     */
    @TableLogic(value = "0", delval = "-1")
    private Integer dataState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArmingPlanName() {
        return armingPlanName;
    }

    public void setArmingPlanName(String armingPlanName) {
        this.armingPlanName = armingPlanName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getStartChannelName() {
        return startChannelName;
    }

    public void setStartChannelName(String startChannelName) {
        this.startChannelName = startChannelName;
    }

    public String getStartChannelCode() {
        return startChannelCode;
    }

    public void setStartChannelCode(String startChannelCode) {
        this.startChannelCode = startChannelCode;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(Integer eventLevel) {
        this.eventLevel = eventLevel;
    }

    public Integer getTimeTempId() {
        return timeTempId;
    }

    public void setTimeTempId(Integer timeTempId) {
        this.timeTempId = timeTempId;
    }

    public Integer getArmingPlanStatus() {
        return armingPlanStatus;
    }

    public void setArmingPlanStatus(Integer armingPlanStatus) {
        this.armingPlanStatus = armingPlanStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDataState() {
        return dataState;
    }

    public void setDataState(Integer dataStatus) {
        this.dataState = dataStatus;
    }
}
