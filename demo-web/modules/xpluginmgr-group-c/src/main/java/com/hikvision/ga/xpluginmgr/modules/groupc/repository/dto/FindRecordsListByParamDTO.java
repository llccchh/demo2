package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyinghao5
 * @date 2022/11/18 15:35
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindRecordsListByParamDTO {

    private String startName;
    private String groupName;
    private String eventType;
    private Integer eventLevel;
    private Integer checkState;


}
