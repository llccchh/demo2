package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import java.util.Date;

/**
 * @author liuyinghao5
 * @date 2022/11/14 15:02
 */

public class LinkRecordDTO {


    private Integer armingPlanId;
    private Date eventHappenTime;

    public LinkRecordDTO(Integer armingPlanId, Date eventHappenTime) {
        this.armingPlanId = armingPlanId;
        this.eventHappenTime = eventHappenTime;
    }

    public LinkRecordDTO() {
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

    @Override
    public String toString() {
        return "LinkRecordDTO{" +
                "armingPlanId=" + armingPlanId +
                ", eventHappenTime=" + eventHappenTime +
                '}';
    }
}
