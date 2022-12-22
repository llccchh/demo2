package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.mapper.TimeStrategyTemplateMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.TimeStrategyTemplateMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.TimeStrategyTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/10 10:22
 */

@Service
public class TimeStrategyTemplateServiceImpl implements TimeStrategyTemplateService{


    @Resource
    private TimeStrategyTemplateMapper timeStrategyTemplateMapper;


    @Override
    public BaseResult<TimeStrategyTemplateMessageVO> getTimeStrategyByArmingPlan(Integer id) {
        return BaseResult.success(timeStrategyTemplateMapper.getMessageByArmingPlanId(id));
    }

    /**
     * 返回时间模板中的有效信息列表
     *
     * @return 返回时间模板中的有效信息列表，具体见vo
     */

    @Override
    public BaseResult<List<TimeStrategyTemplateMessageVO>> getAllMessage() {
        return BaseResult.success(timeStrategyTemplateMapper.getAllMessage());
    }
}
