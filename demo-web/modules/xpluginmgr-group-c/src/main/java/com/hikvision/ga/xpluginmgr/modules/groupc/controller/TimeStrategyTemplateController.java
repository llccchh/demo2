package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.TimeStrategyTemplateMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.TimeStrategyTemplateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/10 10:16
 */

@Controller
@RequestMapping("/templateManage")
public class TimeStrategyTemplateController {

    @Resource
    private TimeStrategyTemplateService timeStrategyTemplateService;


    @ResponseBody
    @RequestMapping("getMessageByArmingPlanId")
    public BaseResult<TimeStrategyTemplateMessageVO> getTimeStrategyByArmingPlan(@RequestParam("armingPlanId") Integer id){

        return timeStrategyTemplateService.getTimeStrategyByArmingPlan(id);
    }


    @ResponseBody
    @RequestMapping("/timeTemplate/getTemplateList.do")
    public BaseResult<List<TimeStrategyTemplateMessageVO>> getTimeMessage(){

        return timeStrategyTemplateService.getAllMessage();
    }

}
