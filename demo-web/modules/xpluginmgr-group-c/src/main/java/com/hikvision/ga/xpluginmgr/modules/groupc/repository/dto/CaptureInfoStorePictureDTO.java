package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import java.util.Date;

/**
 * @author liuyinghao5
 * @date 2022/11/14 14:07
 */

public class CaptureInfoStorePictureDTO {


    private String channelIndexCode;
    private Integer recordId;
    private Date captureTime;
    private String captureUrl;

    public CaptureInfoStorePictureDTO(String channelIndexCode, Integer recordId, Date captureTime, String captureUrl) {
        this.channelIndexCode = channelIndexCode;
        this.recordId = recordId;
        this.captureTime = captureTime;
        this.captureUrl = captureUrl;
    }

    public CaptureInfoStorePictureDTO(String channelIndexCode, Date captureTime, String captureUrl) {
        this.channelIndexCode = channelIndexCode;
        this.captureTime = captureTime;
        this.captureUrl = captureUrl;
    }

    public CaptureInfoStorePictureDTO() {
    }

    public String getChannelIndexCode() {
        return channelIndexCode;
    }

    public void setChannelIndexCode(String channelIndexCode) {
        this.channelIndexCode = channelIndexCode;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Date getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }

    public String getCaptureUrl() {
        return captureUrl;
    }

    public void setCaptureUrl(String captureUrl) {
        this.captureUrl = captureUrl;
    }

    @Override
    public String toString() {
        return "CaptureInfoStorePictureDTO{" +
                "channelIndexCode='" + channelIndexCode + '\'' +
                ", recordId='" + recordId + '\'' +
                ", captureTime=" + captureTime +
                ", captureUrl='" + captureUrl + '\'' +
                '}';
    }
}


