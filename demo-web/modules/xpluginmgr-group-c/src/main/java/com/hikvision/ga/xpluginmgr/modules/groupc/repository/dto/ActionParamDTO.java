package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyinghao5
 * @date 2022/11/28 10:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionParamDTO {
    private String jsonObject;
    private Integer recordId;
    private String indexCode;

}
