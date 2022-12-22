package com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author liting43
 * @date 2022/11/4 15:07
 * @description
 */

@Data
@TableName(value = "xp_link_capture_info")
public class CaptureInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer recordId;
    private String channelIndexCode;
    private Date captureTime;
    private String captureUrl;
    private Date createTime;

    public CaptureInfo(Integer id, Integer recordId, String channelId, Date captureTime, String captureUrl, Date createTime) {
        this.id = id;
        this.recordId = recordId;
        this.channelIndexCode = channelId;
        this.captureTime = captureTime;
        this.captureUrl = captureUrl;
        this.createTime = createTime;
    }

    public CaptureInfo(Integer recordId, String channelIndexCode, Date captureTime, String captureUrl){
        this.recordId = recordId;
        this.channelIndexCode = channelIndexCode;
        this.captureTime = captureTime;
        this.captureUrl = captureUrl;

    }

    public CaptureInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getChannelIndexCode() {
        return channelIndexCode;
    }

    public void setChannelIndexCode(String channelIndexCode) {
        this.channelIndexCode = channelIndexCode;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
