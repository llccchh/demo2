package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/17 10:49
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveArmingPlanDTO {
    private Integer id;
    private String armingPlanName;
    private String startChannelName;
    private String startChannelCode;
    private Integer eventId;
    private Integer eventLevel;
    private Integer timeTempId;
    private String description;

    private List<ActionDTO> linkActionList;
}
