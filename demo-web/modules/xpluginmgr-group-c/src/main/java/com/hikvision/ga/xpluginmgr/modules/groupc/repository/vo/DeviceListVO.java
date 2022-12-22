package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lichanghao6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceListVO {

    private Integer totalNum;
    private List<JSONObject> deviceInfo;

}
