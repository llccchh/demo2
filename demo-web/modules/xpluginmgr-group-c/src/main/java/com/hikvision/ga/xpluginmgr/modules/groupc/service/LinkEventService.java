package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkEvent;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author liting43
 * @date 2022/11/14 10:12
 * @description
 */
@Service
public interface LinkEventService {

    BaseResult<List<LinkEvent>> getDeviceLinkEventList(String deviceIndexCode);

    Integer getEventTypeCode(Integer id);

    BaseResult getChannelLinkEventList(String channelIndexCode);
}
