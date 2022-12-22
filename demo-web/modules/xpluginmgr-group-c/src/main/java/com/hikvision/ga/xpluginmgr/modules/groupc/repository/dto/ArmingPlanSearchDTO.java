package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lichanghao6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArmingPlanSearchDTO {

    private Integer pageSize;
    private Integer pageNum;
    private Integer groupId;
    /**
     * 根据联动规则名称/创建用户筛选，一个参数
     */
    private String selectInfo;
    /**
     * 联动规则状态：0-禁用，1-启用
     */
    private Integer armingPlanStatus;

}
