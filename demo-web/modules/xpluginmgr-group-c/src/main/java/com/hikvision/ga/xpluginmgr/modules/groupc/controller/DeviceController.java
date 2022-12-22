package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.FindChannelByDeviceDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.ChannelMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.DeviceMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.DacService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/9 14:34
 */


@Controller
@RequestMapping("device")
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    @Resource
    private DacService dacService;


    @ResponseBody
    @RequestMapping(value = "getAllDeviceNameAndIndexCode", method = RequestMethod.GET)
    public List<DeviceMessageVO> getAllDeviceName(@RequestParam("type") String type) {
        return deviceService.getDevice(type);
    }


}
