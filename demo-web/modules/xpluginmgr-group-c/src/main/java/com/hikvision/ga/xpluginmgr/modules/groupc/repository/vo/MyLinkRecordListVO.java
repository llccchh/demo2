package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liuyinghao5
 * @date 2022/11/18 15:09
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyLinkRecordListVO {
    private Integer id;
    private String deviceName;
    private String groupName;
    private String eventType;
    private Integer eventLevel;
    private String happenTime;
    private Integer checkState;
    private String confirmInfo;


}
