package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.CaptureInfoStorePictureDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.CaptureInfo;

import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/14 14:05
 */

public interface CaptureInfoService extends IService<CaptureInfo> {

    /**
     * 添加一条联动记录
     * @param dto 添加参数
     * @return 返回是否成功
     */
    Integer insert(CaptureInfoStorePictureDTO dto);

    Boolean insertList(List<CaptureInfo> captureInfos);


}
