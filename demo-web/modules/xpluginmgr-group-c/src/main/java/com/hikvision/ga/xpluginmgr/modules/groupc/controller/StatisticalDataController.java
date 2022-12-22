package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.StatisticalDataByDateDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.RecordLevByDateVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.StatisticalDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 统计数据
 *
 * @author liuyinghao5
 * @date 2022/11/17 19:30
 */

@Controller
@RequestMapping("getCount")
public class StatisticalDataController {

    @Resource
    StatisticalDataService statisticalDataService;


    @GetMapping("aaaa")
    @ResponseBody
    public BaseResult<Map<RecordLevByDateVO, Integer>> get(@RequestParam("state") String state) {
        return BaseResult.success(statisticalDataService.getRecordLevByDate(state ,StatisticalDataByDateDTO.getMyData(state)));
    }

}
