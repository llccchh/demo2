package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.TimeStrategyDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.TimeStrategyMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.TimeStrategyTemplateMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.TimeStrategyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author liuyinghao5
 * @date 2022/11/10 11:17
 */

@Controller
@RequestMapping("timeStrategy")
public class TimeStrategyController {



    @Resource
    TimeStrategyService timeStrategyService;

    @RequestMapping("insertByTemplate")
    @ResponseBody
    public BaseResult<Integer> insertDataByTemplate(@RequestParam("templateId") Integer templateId){
        return timeStrategyService.insertByTemplate(templateId);
    }


    @RequestMapping("saveData")
    @ResponseBody
    public BaseResult<Integer> saveData(@RequestBody TimeStrategyDTO timeStrategyDTO){
        return timeStrategyService.saveTimeData(timeStrategyDTO);
    }

    @ResponseBody
    @RequestMapping("getMessageByArmingPlanId")
    public BaseResult<TimeStrategyMessageVO> getTimeStrategyMessageByArmingPlanId(@RequestParam("armingPlanId") Integer id){

        return timeStrategyService.getTimeStrategyMessageByArmingPlanId(id);
    }



}
