package com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author lichanghao6
 */
@Data
@TableName("xp_link_r_group_device")
public class ReGroupDevice {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer groupId;
    private String channelCode;
    private String deviceIndexCode;
    private Timestamp createTime;
    private Timestamp updateTime;

    public ReGroupDevice(Integer id, Integer groupId, String channelCode, String deviceIndexCode, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.groupId = groupId;
        this.channelCode = channelCode;
        this.deviceIndexCode = deviceIndexCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ReGroupDevice() {
    }
}
