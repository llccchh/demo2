package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/21 13:52
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArmingPlanInfoVO {
    private String armingPlanName;
    private Integer groupId;
    private String description;
    private String startChannelName;
    private String startChannelCode;
    private String eventName;
    private String eventCode;
    /**
     * 事件等级
     */
    private Integer eventLevel;
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
     * 联动时间策略模板
     */
    private String tempName;
    /**
     * 动作参数
     */
    private List<ActionDTO> actionList;
}
