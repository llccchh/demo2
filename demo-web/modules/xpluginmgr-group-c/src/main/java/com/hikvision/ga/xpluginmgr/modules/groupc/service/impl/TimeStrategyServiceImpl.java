package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.aries.jc.common.BaseResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikvision.ga.xpluginmgr.modules.groupc.mapper.TimeStrategyMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.TimeStrategyDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.TimeStrategy;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.GetTimeStrategyTemplateDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.TimeStrategyMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.TimeStrategyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuyinghao5
 * @date 2022/11/10 11:18
 */

@Service
public class TimeStrategyServiceImpl extends ServiceImpl<TimeStrategyMapper, TimeStrategy> implements TimeStrategyService {


    @Resource
    TimeStrategyMapper timeStrategyMapper;

    /**
     * 通过模板id将数据填入时间策略表中，并返回对应的时间策略id
     *
     * @param id 时间策略模板id
     * @return 时间策略id
     */

    @Override
    public BaseResult<Integer> insertByTemplate(Integer id) {
        GetTimeStrategyTemplateDTO dto = new GetTimeStrategyTemplateDTO();
        dto.setTemplateId(id);
        timeStrategyMapper.insertDataByTemplate(dto);
        return BaseResult.success(dto.getId());
    }

    /**
     * 保存实践策略，返回新增的id供布撤防使用
     *
     * @param dto 前端返回的实践策略参数
     * @return 返回新增的id节点
     */
    @Override
    public BaseResult<Integer> saveTimeData(TimeStrategyDTO dto) {
        TimeStrategy timeStrategy = dto.changeToEntity();
        timeStrategyMapper.insert(timeStrategy);
        return BaseResult.success(timeStrategy.getId());
    }

    /**
     * 通过布撤防id返回对应的时间策略信息
     *
     * @param id 布撤防id
     * @return 返回对应时间策略参数，具体见vo
     */
    @Override
    public BaseResult<TimeStrategyMessageVO> getTimeStrategyMessageByArmingPlanId(Integer id) {

        return BaseResult.success(timeStrategyMapper.getMessageByArmingPlanId(id));
    }

}
