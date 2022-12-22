package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ReActionAbility;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.ReActionAbilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author liting43
 * @date 2022/11/14 14:51
 * @description
 */
@RestController
@RequestMapping("/cameraLink/v1/action")
public class ReActionAbilityController {

    @Resource
    private ReActionAbilityService reActionAbilityService;

    /**
     * @author liting43
     * 根据通道能力集获取联动动作
     */
    @GetMapping("/getActionList.do")
    public BaseResult<List<ReActionAbility>> getLinkActionList(String channelIndexCode){
        return reActionAbilityService.getChannelActionList(channelIndexCode);
    }
}
