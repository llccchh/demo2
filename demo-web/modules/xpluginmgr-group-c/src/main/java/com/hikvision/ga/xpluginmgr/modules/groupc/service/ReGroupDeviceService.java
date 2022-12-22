package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ReGroupDevice;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/11 11:13
 * @description
 */

public interface ReGroupDeviceService extends IService<ReGroupDevice> {

    List<ReGroupDevice> selectByGroupId(Integer groupId);

    void deleteByGroupIdAndBatchChannelCode(Integer groupId, List<String> delChannelCode);

    boolean insertBatch(List<ReGroupDevice> reGroupDeviceList);

    void deleteChannelInGroupId(List<Integer> ids);
}
