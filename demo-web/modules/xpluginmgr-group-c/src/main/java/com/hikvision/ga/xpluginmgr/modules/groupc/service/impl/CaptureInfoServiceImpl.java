package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikvision.ga.xpluginmgr.modules.groupc.constant.MyMapperStruct;
import com.hikvision.ga.xpluginmgr.modules.groupc.mapper.CaptureInfoMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.CaptureInfoStorePictureDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.CaptureInfo;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.CaptureInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/14 14:05
 */

@Service
public class CaptureInfoServiceImpl extends ServiceImpl<CaptureInfoMapper, CaptureInfo> implements CaptureInfoService {

    @Resource
    CaptureInfoMapper captureInfoMapper;


    /**
     * 添加一条联动记录
     *
     * @param dto 添加参数
     * @return 返回是否成功, 0和1
     */
    @Override
    public Integer insert(CaptureInfoStorePictureDTO dto) {
        return captureInfoMapper.insert(MyMapperStruct.INSTANCE.changeDto2Entity(dto));
    }

    @Override
    public Boolean insertList(List<CaptureInfo> captureInfos) {
        return saveBatch(captureInfos, captureInfos.size());
    }

}
