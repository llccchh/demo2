package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/11 14:52
 */

public interface NmsService {

    /**
     * 按照编码列表查询查询设备是否在线
     *
     * @param indexCode 编码列表
     * @return 返回设备在线状态
     */
    Map<Object, Object> getDeviceOnlineState(List<String> indexCode);


    /**
     * 查询所有设备是否在线
     *
     * @return 返回设备在线状态
     */
    @Deprecated
    Map<Object, Object> getAllDeviceOnlineState();



    /**
     * 查询所有某种设备类型是否在线
     *
     * @return 返回设备在线状态
     */
    Map<Object, Object> getAllResourceOnlineState(String type, List<String> codes);


}
