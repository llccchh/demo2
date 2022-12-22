package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ReActionAbility;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/14 16:07
 * @description
 */
@Service
public interface ReActionAbilityService {
    BaseResult<List<ReActionAbility>> getDeviceActionList(String deviceIndexCode);

    BaseResult<List<ReActionAbility>> getChannelActionList(String channelIndexCode);
}
