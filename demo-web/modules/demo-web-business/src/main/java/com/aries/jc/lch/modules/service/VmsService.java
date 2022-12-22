package com.aries.jc.lch.modules.service;

import com.alibaba.fastjson2.JSONObject;
import com.aries.jc.common.BaseResult;

/**
 * @author lichanghao6
 */
public interface VmsService {

    BaseResult<Object> takePicture(JSONObject jsonObject);
    BaseResult<Object> startVideo(JSONObject jsonObject);
    BaseResult<Object> getVideoMessage(JSONObject jsonObject);
    BaseResult<Object> endVideo(JSONObject jsonObject);
}
