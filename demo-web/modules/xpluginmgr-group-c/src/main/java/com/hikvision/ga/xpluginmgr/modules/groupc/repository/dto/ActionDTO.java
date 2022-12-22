package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liting43
 * @date 2022/11/17 16:23
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionDTO {
    private String actionName;
    private String linkChannelName;
    private String linkChannelCode;
    private JSONObject actionParam;
}
