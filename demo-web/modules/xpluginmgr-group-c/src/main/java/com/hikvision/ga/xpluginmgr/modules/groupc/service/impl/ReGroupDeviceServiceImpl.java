package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikvision.ga.xpluginmgr.modules.groupc.mapper.ReGroupDeviceMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ReGroupDevice;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.ReGroupDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/18 10:56
 * @description
 */
@Service
public class ReGroupDeviceServiceImpl extends ServiceImpl<ReGroupDeviceMapper, ReGroupDevice> implements ReGroupDeviceService {
    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(ReGroupDeviceService.class);

    @Autowired
    private ReGroupDeviceMapper reGroupDeviceMapper;

    /**
     * @author liting43
     * @description 根据分组ID获取分组设备关系数据
     * @param groupId
     * return list
     */
    @Override
    public List<ReGroupDevice> selectByGroupId(Integer groupId){
        LambdaQueryWrapper<ReGroupDevice> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ReGroupDevice::getGroupId, groupId);
        return reGroupDeviceMapper.selectList(lambdaQueryWrapper);
    }

    /**
     * @author liting43
     * @description 根据分组ID和分组内的通道编码批量删除分组和通道关系
     * @param groupId
     * @param delChannelCode
     * return void
     */
    @Override
    public void deleteByGroupIdAndBatchChannelCode(Integer groupId, List<String> delChannelCode) {
        LambdaQueryWrapper<ReGroupDevice> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ReGroupDevice::getGroupId, groupId);
        lambdaQueryWrapper.in(ReGroupDevice::getChannelCode, delChannelCode);
        reGroupDeviceMapper.delete(lambdaQueryWrapper);
    }

    /**
     * @author liting43
     * @description 批量插入分组和通道关系数据
     * @param reGroupDeviceList
     * return list
     */
    @Override
    public boolean insertBatch(List<ReGroupDevice> reGroupDeviceList) {
        if (reGroupDeviceList.isEmpty()){
            return true;
        }
        return saveBatch(reGroupDeviceList);
    }

    /**
     * @author liting43
     * @description 根据分组ID批量删除分组和通道关系数据
     * @param ids groupId
     * return list
     */
    @Override
    public void deleteChannelInGroupId(List<Integer> ids) {
        reGroupDeviceMapper.deleteChannelInGroupId(ids);
    }
}
