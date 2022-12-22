package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyinghao5
 * @date 2022/11/21 10:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResourceByRegionDTO {

    private String orgIndexCode;
    private String resourceType;
    private String deviceMessage;


}
