package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author liting43
 * @date 2022/11/19 17:51
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkRecordInfoVO {
    private Integer id;
    private String eventName;
    private String eventType;
    private Integer eventLevel;
    private String eventLevelZh;
    private String startChannelName;
    private String startChannelCode;
    private String groupName;
    private String eventHappenTime;
    private Integer confirmStatus;
    private String confirmStatusZh;
    private String confirmInfo;
    private String confirmOpinion;
    private String confirmTime;

    private List<ActionDTO> actionList;

    private List<String> captureImage;
}
