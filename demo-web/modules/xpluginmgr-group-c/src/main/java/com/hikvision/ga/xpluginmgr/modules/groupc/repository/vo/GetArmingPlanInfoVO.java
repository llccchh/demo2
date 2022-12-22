package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lichanghao6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetArmingPlanInfoVO {

    private String armingPlanName;
    private String description;
    private String startChannelCode;
    private String eventName;
    private String eventCode;
    /**
     * 事件标识
     */
    private String eventIdentity;
    /**
     * 事件类目
     */
    private String eventType;
    /**
     * 事件适用场景
     */
    private String application;
    /**
     * 联动设备通道编码
     */
    private String linkChannelCode;
    /**
     * 时间模板
     */
    private String tempName;
    /**
     * 动作名称
     */
    private String actionName;
    /**
     * 动作参数
     */
    private String actionParam;


}
