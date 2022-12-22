package com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liting43
 * @date 2022/11/4 11:25
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "xp_link_event")
public class LinkEvent {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String eventName;
    private String eventIdentity;
    private String eventType;
    private Integer eventTypeCode;
    private String eventCode;
    private String capability;
    private String application;
    private Date createTime;
}
