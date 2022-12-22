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
public class GroupSearchDTO {

    private Integer pageSize;
    private Integer pageNum;
    /**
     * 根据分组名称或创建用户筛选，一个参数
     */
    private String selectInfo;

}
