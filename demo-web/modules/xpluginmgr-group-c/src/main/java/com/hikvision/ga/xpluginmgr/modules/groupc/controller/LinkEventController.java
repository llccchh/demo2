package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkEvent;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.DeviceService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkEventService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liting43
 * @date 2022/11/14 9:57
 * @description
 */
@RestController
@RequestMapping("/cameraLink/v1/linkEvent")
public class LinkEventController {

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(LinkGroupController.class);

    @Resource
    private LinkEventService linkEventService;

    /**
     * @author liting43
     * 根据设备通道能力集获取联动事件
     */
    @GetMapping("/getLinkEventList.do")
    public BaseResult getLinkEventList(@Param("channelIndexCode") String channelIndexCode){
        return linkEventService.getChannelLinkEventList(channelIndexCode);
    }
}
