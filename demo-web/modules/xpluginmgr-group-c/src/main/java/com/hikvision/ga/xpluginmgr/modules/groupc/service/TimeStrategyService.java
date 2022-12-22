package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.TimeStrategyDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.TimeStrategyMessageVO;

/**
 * @author liuyinghao5
 * @date 2022/11/10 11:18
 */

public interface TimeStrategyService{


    /**
     * 通过模板id将数据填入时间策略表中，并返回对应的时间策略id
     * @param id 时间策略模板id
     * @return 时间策略id
     */
    BaseResult<Integer> insertByTemplate(Integer id);


    /**
     * 保存实践策略，返回新增的id供布撤防使用
     * @param dto 前端返回的实践策略参数
     * @return  返回新增的id节点
     */
    BaseResult<Integer> saveTimeData(TimeStrategyDTO dto);


    /**
     * 通过布撤防id返回对应的时间策略信息
     * @param id 布撤防id
     * @return   返回对应时间策略参数，具体见vo
     */
    BaseResult<TimeStrategyMessageVO>  getTimeStrategyMessageByArmingPlanId(Integer id);

}
