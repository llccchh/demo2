package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.TimeStrategyTemplateMessageVO;

import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/10 10:22
 */

public interface TimeStrategyTemplateService{


    /**
     * 通过布撤防信息查询时间模板信息
     * @param id 布撤防id
     * @return 返回对应的时间模板信息
     */
    BaseResult<TimeStrategyTemplateMessageVO> getTimeStrategyByArmingPlan(Integer id);

    /**
     * 返回时间模板中的有效信息列表
     * @return 返回时间模板中的有效信息列表，具体见vo
     */
    BaseResult<List<TimeStrategyTemplateMessageVO>> getAllMessage();

}
