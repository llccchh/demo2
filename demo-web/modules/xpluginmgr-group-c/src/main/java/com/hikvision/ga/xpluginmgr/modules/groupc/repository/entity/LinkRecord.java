package com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author liting43
 * @date 2022/11/4 11:30
 * @description
 */

@Data
@TableName(value = "xp_link_record")
public class LinkRecord {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer armingPlanId;
    private Date eventHappenTime;
    private Integer confirmStatus;
    private String confirmInfo;
    private String confirmOpinion;
    private Date confirmTime;
    private Date createTime;

    public LinkRecord(Integer id, Integer armingPlanId, Date eventHappenTime, Integer confirmStatus, String confirmInfo, String confirmOpinion, Date confirmTime, Date createTime) {
        this.id = id;
        this.armingPlanId = armingPlanId;
        this.eventHappenTime = eventHappenTime;
        this.confirmStatus = confirmStatus;
        this.confirmInfo = confirmInfo;
        this.confirmOpinion = confirmOpinion;
        this.confirmTime = confirmTime;
        this.createTime = createTime;
    }

    public LinkRecord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArmingPlanId() {
        return armingPlanId;
    }

    public void setArmingPlanId(Integer armingPlanId) {
        this.armingPlanId = armingPlanId;
    }

    public Date getEventHappenTime() {
        return eventHappenTime;
    }

    public void setEventHappenTime(Date eventHappenTime) {
        this.eventHappenTime = eventHappenTime;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getConfirmInfo() {
        return confirmInfo;
    }

    public void setConfirmInfo(String confirmInfo) {
        this.confirmInfo = confirmInfo;
    }

    public String getConfirmOpinion() {
        return confirmOpinion;
    }

    public void setConfirmOpinion(String confirmOpinion) {
        this.confirmOpinion = confirmOpinion;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
