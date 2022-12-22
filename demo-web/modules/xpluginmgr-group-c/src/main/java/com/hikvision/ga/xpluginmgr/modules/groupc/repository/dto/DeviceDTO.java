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
public class DeviceDTO {

    private Integer pageSize;
    private Integer pageNum;
    private Integer groupId;
    /**
     * 根据设备名称或区域筛选，一个参数
     */
    private String selectInfo;

}
