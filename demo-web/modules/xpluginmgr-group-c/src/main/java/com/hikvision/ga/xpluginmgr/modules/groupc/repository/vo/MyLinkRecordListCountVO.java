package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyinghao5
 * @date 2022/11/30 19:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyLinkRecordListCountVO {

    private Integer id;
    private String deviceName;
    private String groupName;
    private String eventType;
    private Integer eventLevel;
    private String happenTime;
    private Integer checkState;
    private String confirmInfo;
}
