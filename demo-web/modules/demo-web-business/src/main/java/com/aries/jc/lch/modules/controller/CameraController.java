package com.aries.jc.lch.modules.controller;

import com.alibaba.fastjson2.JSONObject;
import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.repository.dto.EndVideoDTO;
import com.aries.jc.lch.modules.repository.dto.PreIndexDTO;
import com.aries.jc.lch.modules.repository.dto.StartVideoDTO;
import com.aries.jc.lch.modules.repository.entity.HttpRequestEntity;
import com.aries.jc.lch.modules.service.BicService;
import com.aries.jc.lch.modules.service.CameraService;
import com.aries.jc.lch.modules.service.VmsService;
import com.aries.jc.lch.modules.utils.CommonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author lichanghao6
 */
@RestController
@RequestMapping("vms")
public class CameraController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private BicService bicService;

    @Resource
    private CameraService cameraService;

    @Resource
    private VmsService vmsService;

    @RequestMapping("restTemplate")
    public BaseResult lch(@RequestBody HttpRequestEntity httpRequestEntity) {
        HttpHeaders headers = CommonUtil.createHeader();
        HttpEntity entity = new HttpEntity(httpRequestEntity, headers);

        ResponseEntity<BaseResult> responseEntity = restTemplate.exchange("http://10.184.49.250:8494/univice/application/event/v1/message/trigger/record", HttpMethod.POST, entity, BaseResult.class);
        return responseEntity.getBody();

    }

    @RequestMapping(value = "takePicture", method = {RequestMethod.POST})
    public BaseResult<Object> takePicture(@RequestBody PreIndexDTO preIndexDTO) {
        // JSONObject jsonObject = new JSONObject();
        // jsonObject.put("cameraIndexCode", preIndexDTO.getCameraIndexCode());
        // return (bicService.fetchComponentResult(null, null, "/api/v3/manualCapture", jsonObject));
        return cameraService.takePicture(preIndexDTO.getCameraIndexCode());
    }

    @PostMapping("startVideo")
    public BaseResult<Object> startVideo(@RequestBody StartVideoDTO startVideoDTO) {
        return cameraService.startVideo(startVideoDTO);
    }

    @PostMapping("endVideo")
    public BaseResult<Object> endVideo(@RequestBody EndVideoDTO endVideoDTO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cameraIndexCode", endVideoDTO.getCameraIndexCode());
        jsonObject.put("type", endVideoDTO.getType());
        return vmsService.endVideo(jsonObject);
    }

    // private BaseResult<Object> TakeVideoDTO(StartVideoDTO startVideoDTO) {
    //     System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
    //     String taskId = cameraService.startVideo(startVideoDTO).getData().toString();
    //     EndVideoDTO endVideoDTO = new EndVideoDTO();
    //     endVideoDTO.setCameraIndexCode(startVideoDTO.getCameraIndexCode());
    //     endVideoDTO.setTaskId(taskId);
    //     endVideoDTO.setType(startVideoDTO.getType());
    //     try {
    //         Thread.sleep(startVideoDTO.getTime());
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //         System.out.println("?????????????????");
    //         logger.error("拍摄出错，请联系管理员");
    //         return BaseResult.error("aa00000001", "录像错误，请联系管理员");
    //     }
    //
    //     cameraService.endVideo(endVideoDTO);
    //     BaseResult<Object> baseResult = cameraService.getMessage(endVideoDTO);
    //     System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
    //     logger.info("录像计划成功, 录像时间为" + takeVideoDTO.getTime() + "返回值为" + baseResult);
    //     logger.error("成功");
    //     return baseResult;
    // }

}

