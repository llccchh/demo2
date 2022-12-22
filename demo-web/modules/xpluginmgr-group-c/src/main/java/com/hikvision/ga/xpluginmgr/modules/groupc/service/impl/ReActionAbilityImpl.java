package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.mapper.ReActionAbilityMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ReActionAbility;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.DacService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.DeviceService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.ReActionAbilityService;
import com.hikvision.security.patch.io.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liting43
 * @date 2022/11/14 16:07
 * @description
 */
@Service
public class ReActionAbilityImpl implements ReActionAbilityService {

    @Resource
    private DacService dacService;

    @Resource
    private ReActionAbilityMapper reActionAbilityMapper;

    @Resource
    private DeviceService deviceService;

    /**
     * @author liting43
     * 根据设备能力集获取联动动作
     *
     * @param deviceIndexCode 设备编码
     * @return 设备具备的动作
     */
    @Override
    public BaseResult<List<ReActionAbility>> getDeviceActionList(String deviceIndexCode){
        JSONObject obj = (JSONObject) dacService.getAbilityByDevice(deviceIndexCode).getData();
        JSONArray capability = obj.getJSONArray(deviceIndexCode);
        String[] ability =  capability.toArray(new String[0]);
        return BaseResult.success(filterActions(ability));
    }

    /**
     * @author liting43
     * 根据通道能力集获取联动动作
     *
     * @param channelIndexCode 设备编码
     * @return 通道具备的动作
     */
    @Override
    public BaseResult<List<ReActionAbility>> getChannelActionList(String channelIndexCode){
        String[] capability = deviceService.getChannelEvent(channelIndexCode).split("@");
        return BaseResult.success(filterActions(capability));
    }

    /**
     * @author liting43
     * 筛选联动动作
     *
     * @param capability 能力集
     * @return 动作
     */
    private List<ReActionAbility> filterActions(String[] capability) {
        List<String> capabilityList = ArrayUtils.asList(capability);
        capabilityList.add("*");
        List<ReActionAbility> reActionAbilities = new ArrayList<>();
        for (String ability: capabilityList){
            LambdaQueryWrapper<ReActionAbility> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ReActionAbility::getCapability, ability);
            List<ReActionAbility> raa = reActionAbilityMapper.selectList(lambdaQueryWrapper);
            for (ReActionAbility r: raa){
                reActionAbilities.add(r);
            }
        }
        return reActionAbilities;
    }
}
