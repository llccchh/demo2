package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

/**
 * @author liting43
 * @date 2022/11/18 16:22
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectDataVO {
    /**
     * 事件源设备
     */
    private HashSet<String> cameraNames;
    /**
     * 所属分组名称
     */
    private HashSet<String> groupNames;
    /**
     * 触发事件名称
     */
    private HashSet<String> eventNames;

}
