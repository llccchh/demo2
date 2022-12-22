package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.StatisticalDataByDateDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.RecordLevByDateVO;

import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/17 20:18
 */

public interface StatisticalDataService {

    BaseResult getDataAndLev();
    Map<RecordLevByDateVO, Integer> getRecordLevByDate(String state, StatisticalDataByDateDTO statisticalDataByDateDTO);


}
