package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;

import java.util.List;

/**
 * @author liuyinghao5
 * 访问dac接口汇总
 */
public interface DacService {

    /**
     * 通过设备查询设备能力集
     * @param deviceIndexCode  设备编码
     * @return  返回结果
     */
    BaseResult<Object> getAbilityByDevice(String deviceIndexCode);


    Object getAbility();

    /**
     * @param deviceCodes  编码
     * @return  返回对应的能力
     */
    List<String> getOperateByIndexCode(String[] deviceCodes);


}
