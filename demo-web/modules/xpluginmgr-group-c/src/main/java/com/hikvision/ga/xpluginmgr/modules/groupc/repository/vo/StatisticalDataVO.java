package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import java.util.Date;

/**
 * @author liuyinghao5
 * @date 2022/11/18 14:08
 */

public class StatisticalDataVO {
    private String startIndexCode;
    private String regionName;
    private String eventType;
    private String eventLevel;
    private Date happenTime;
    private String checkAdvice;


    public StatisticalDataVO(String startIndexCode, String regionName, String eventType, String eventLevel, Date happenTime, String checkAdvice) {
        this.startIndexCode = startIndexCode;
        this.regionName = regionName;
        this.eventType = eventType;
        this.eventLevel = eventLevel;
        this.happenTime = happenTime;
        this.checkAdvice = checkAdvice;
    }

    public StatisticalDataVO() {
    }

    public String getStartIndexCode() {
        return startIndexCode;
    }

    public void setStartIndexCode(String startIndexCode) {
        this.startIndexCode = startIndexCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public Date getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
    }

    public String getCheckAdvice() {
        return checkAdvice;
    }

    public void setCheckAdvice(String checkAdvice) {
        this.checkAdvice = checkAdvice;
    }

    @Override
    public String toString() {
        return "StatisticalDataVO{" +
                "startIndexCode='" + startIndexCode + '\'' +
                ", regionName='" + regionName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventLevel='" + eventLevel + '\'' +
                ", happenTime=" + happenTime +
                ", checkAdvice='" + checkAdvice + '\'' +
                '}';
    }
}
