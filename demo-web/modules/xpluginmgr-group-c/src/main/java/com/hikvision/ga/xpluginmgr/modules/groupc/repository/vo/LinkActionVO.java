package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuyinghao5
 * @date 2022/11/24 11:24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkActionVO {

    private String jsonObject;

    private String indexCode;
    private String name;
    private Integer armingPlanId;
}
