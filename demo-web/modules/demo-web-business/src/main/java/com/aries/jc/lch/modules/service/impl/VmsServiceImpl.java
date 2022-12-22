package com.aries.jc.lch.modules.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.service.BicService;
import com.aries.jc.lch.modules.service.VmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lichanghao6
 */
@Service
public class VmsServiceImpl implements VmsService {

    @Resource
    private BicService<Object> bicService;

    @Override
    public BaseResult<Object> takePicture(JSONObject jsonObject) {
        return bicService.fetchComponentResult(null, null, "/api/v3/manualCapture", jsonObject);
    }

    @Override
    public BaseResult<Object> startVideo(JSONObject jsonObject) {
        return bicService.fetchComponentResult(null, null, "/api/v4/manualRecord/start", jsonObject);
    }

    @Override
    public BaseResult<Object> getVideoMessage(JSONObject jsonObject) {
        return bicService.fetchComponentResult(null, null, "/api/v4/manualRecord/status", jsonObject);

    }

    @Override
    public BaseResult<Object> endVideo(JSONObject jsonObject) {
        return bicService.fetchComponentResult(null, null, "/api/v4/manualRecord/stop", jsonObject);
    }
}
