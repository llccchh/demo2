package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.StatisticalDataByDateDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.RecordLevByDateVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkRecordService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.StatisticalDataService;
import com.hikvision.ga.xpluginmgr.tool.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liuyinghao5
 * @date 2022/11/17 20:18
 */

@Service
public class StatisticalDataServiceImpl implements StatisticalDataService {

    @Resource
    LinkRecordService linkRecordService;


    @Override
    public BaseResult getDataAndLev() {
            return null;
    }


    @Override
    public Map<RecordLevByDateVO, Integer> getRecordLevByDate(String state,  StatisticalDataByDateDTO statisticalDataByDateDTO) {
        List<RecordLevByDateVO> record;
        if ("year".equals(state)){
            record = linkRecordService.getRecordLevByYearDate(statisticalDataByDateDTO);
        }
        else {
            record = linkRecordService.getRecordLevByDate(statisticalDataByDateDTO);
        }

        Map<RecordLevByDateVO, Integer> map = new HashMap<>(8);
        for (RecordLevByDateVO vo : record) {

            if (map.containsKey(vo)) {
                map.put(vo, map.get(vo) + 1);
            } else {
                map.put(vo, 1);
            }
        }
        return map;
    }

}
