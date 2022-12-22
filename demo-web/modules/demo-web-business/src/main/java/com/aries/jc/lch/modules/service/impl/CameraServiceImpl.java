package com.aries.jc.lch.modules.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.repository.dto.EndVideoDTO;
import com.aries.jc.lch.modules.repository.dto.StartVideoDTO;
import com.aries.jc.lch.modules.service.CameraService;
import com.aries.jc.lch.modules.service.VmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lichanghao6
 */
@Service
public class CameraServiceImpl implements CameraService {

    @Resource
    private VmsService vmsService;

    @Override
    public BaseResult<Object> takePicture(String cameraIndexCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cameraIndexCode", cameraIndexCode);
        return vmsService.takePicture(jsonObject);
    }

    @Override
    public BaseResult<Object> startVideo(StartVideoDTO startVideoDTO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cameraIndexCode", startVideoDTO.getCameraIndexCode());
        jsonObject.put("recordType", startVideoDTO.getRecordType());
        jsonObject.put("type", startVideoDTO.getType());
        vmsService.startVideo(jsonObject);

        return vmsService.endVideo(jsonObject);
    }

    @Override
    public BaseResult<Object> getMessage(EndVideoDTO endVideoDTO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cameraIndexCode", endVideoDTO.getCameraIndexCode());
        jsonObject.put("taskId", endVideoDTO.getTaskId());
        jsonObject.put("type", endVideoDTO.getType());
        return vmsService.getVideoMessage(jsonObject);
    }

    @Override
    public BaseResult<Object> endVideo(EndVideoDTO endVideoDTO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cameraIndexCode", endVideoDTO.getCameraIndexCode());
        jsonObject.put("taskId", endVideoDTO.getTaskId());
        jsonObject.put("type", endVideoDTO.getType());
        return vmsService.endVideo(jsonObject);
    }
}
