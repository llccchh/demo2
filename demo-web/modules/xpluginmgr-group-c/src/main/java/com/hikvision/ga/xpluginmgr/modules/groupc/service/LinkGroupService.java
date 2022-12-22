package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.AddLinkGroupDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.BatchIdDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.DeviceDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.GroupSearchDTO;
import org.springframework.stereotype.Service;

/**
 * @author lichanghao6
 */
@Service
public interface LinkGroupService {

    BaseResult saveLinkGroup(AddLinkGroupDTO addLinkGroupDTO);

    BaseResult delLinkGroup(BatchIdDTO batchIdDTO);

    BaseResult getGroupList(GroupSearchDTO groupSearchDTO);

    BaseResult getGroupInfo(Integer id);

    BaseResult getGroupDeviceList(DeviceDTO deviceDTO);

    BaseResult save(Integer id, String name, String description);
}

