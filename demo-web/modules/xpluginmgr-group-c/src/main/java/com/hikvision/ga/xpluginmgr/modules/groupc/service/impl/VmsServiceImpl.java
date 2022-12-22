package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.CaptureInfoStorePictureDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.TurnToPreIndexDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.BicService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.CaptureInfoService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.VmsService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/7 16:00
 */

@Service
public class VmsServiceImpl implements VmsService {


    @Resource
    private BicService<Object> bicService;


    private BaseResult<Object> vmsPost(String uri, Object param) {
        return bicService.fetchComponentResult("vms", "vmsweb", uri, param);
    }


    @Override
    public String takePicture(String indexCode) {
        JSONObject js = new JSONObject();
        js.put("cameraIndexCode", indexCode);

        JSONObject jsonObject = (JSONObject) vmsPost("/api/v3/manualCapture", js).getData();
        return jsonObject.get("picUrl").toString();
    }

    /**
     * @author lichanghao6
     * 转到预置点
     *
     * @param takePictureDTO 转到预置点
     * @return 操作是否成功
     */
    @Override
    public Boolean turnToIndex(TurnToPreIndexDTO takePictureDTO) {


        return vmsPost("/api/v1/ptzs/controlling", takePictureDTO).isSuccess();

    }

    @Override
    public Boolean setIndex(Object o) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("presetName" , "55");
        jsonObject.put("presetIndex" , "1");
        jsonObject.put("cameraIndexCode" , "8e0c6f88f9bd44088ccbd567a362d25d");

        return vmsPost("/api/v3/presets/add", jsonObject).isSuccess();

    }
}
