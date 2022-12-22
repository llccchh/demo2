package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.DeviceIfOnlineDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.NmsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/11 16:52
 */

@Controller
@RequestMapping("nms")
public class NmsTestController {

    @Resource
    NmsService nmsService;


    @RequestMapping(value = "nms", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getDeviceOnlineState(@RequestBody List<String> deviceIfOnlineDTO) {
        return nmsService.getDeviceOnlineState(deviceIfOnlineDTO);
    }

    @RequestMapping(value = "nmsAll", method = RequestMethod.GET)
    @ResponseBody
    public Map<Object, Object> getAllDeviceOnlineState() {
        return nmsService.getAllDeviceOnlineState();
    }
}
