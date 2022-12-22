package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.SendEmileDTO;

/**
 * @author liuyinghao5
 * @date 2022/11/7 10:58
 */

public interface MailpsService {

    BaseResult<Object> sendEmile(SendEmileDTO sendEmileDTO);

}
