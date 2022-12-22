package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/17 19:51
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddArmingPlanDTO {
    private String armingPlanName;
    private Integer groupId;
    private String startChannelName;
    private String startChannelCode;
    private Integer eventId;
    private Integer eventLevel;
    private Integer timeTempId;
    private String description;

    private List<ActionDTO> linkActionList;
}
