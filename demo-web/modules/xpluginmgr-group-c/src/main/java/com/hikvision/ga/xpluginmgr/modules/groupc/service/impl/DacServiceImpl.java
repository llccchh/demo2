package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.BicService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.DacService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.hikvision.ga.xpluginmgr.modules.groupc.constant.StringConstant.DAC_DMS_SEARCH_ABILITY_BY_DEVICE_CODE_URL;

/**
 * @author liuyinghao5
 * @date 2022/11/4 14:26
 */

@Service
public class DacServiceImpl implements DacService {

    @Resource
    BicService<Object> bicService;


    /**
     * 请求dms接口
     * @param uri 请求后缀
     * @param jsonObject 请求体
     * @return 返回请求结果
     */
    private BaseResult<Object> dmsPost(String uri, Object jsonObject){
        return bicService.fetchComponentResult("dac", "dms", uri, jsonObject);
    }


    /**
     * @param deviceIndexCode 设备编码
     * @return 返回对应设备的能力集
     */
    @Override
    public BaseResult<Object> getAbilityByDevice(String deviceIndexCode) {
        JSONObject body = new JSONObject();
        String[] a = new String[1];
        a[0] = deviceIndexCode;
        body.put("deviceIndexCodes", a);

        return dmsPost(DAC_DMS_SEARCH_ABILITY_BY_DEVICE_CODE_URL, body);
    }

    @Override
    public Object getAbility() {
        JSONObject jsonObject = new JSONObject();
        String[] a = new String[1];
        a[0] = "70632d22d3354701a24512583f1f58dd";
        jsonObject.put("deviceIndexCodes", a);
        return dmsPost("/dms/v2/deviceAbility", jsonObject);
    }

    /**
     * @param deviceCodes 编码列表
     * @return 返回对应的能力
     */
    @Override
    public List<String> getOperateByIndexCode(String[] deviceCodes) {
        getAbility();
        return null;
    }

}
