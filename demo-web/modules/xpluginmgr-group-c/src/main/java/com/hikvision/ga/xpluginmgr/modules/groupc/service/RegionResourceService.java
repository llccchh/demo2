package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.FindResourceDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.GetResourceByRegionDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.ChannelMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.DeviceMessageResultVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.RegionVO;

import java.util.List;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/8 16:13
 */

public interface RegionResourceService {

    // todo 需要在缓存信息为0时更新缓存。
    /**
     * 返回区域目录，从redis中获取
     * @return 区域目录信息，具体见vo
     */
    BaseResult<List<RegionVO>> getRootIndexCode(String getRootIndexCode);


    /**
     * 通过区域查找通道信息
     * @param findResourceDTO 此处为前端传来的值
     * @return 返回通道消息列表
     */
    BaseResult<List<ChannelMessageVO>> getResourceByRegion(FindResourceDTO findResourceDTO);

    /**
     * 通过前端参数获取设备信息
     * @return  返回设备信息
     */
    BaseResult<List<DeviceMessageResultVO>> getDeviceMessageByRegion( GetResourceByRegionDTO getResourceByRegionDTO);



    /**
     * 通过区域信息获取区域内的所有设备信息
     *
     * @param indexCode 设备编码，可为空  为空查询全部
     * @param regionCode  区域编码 可为空 为空查询全部
     * @param name 设备名字模糊搜索
     * @param regionMap  区域map映射，用来将查到的indexCode获取为调用名字  com.hikvision.ga.xpluginmgr.modules.groupc.service.impl.RegionResourceServiceImpl#getRegionMessageMap()函数
     */
    BaseResult<List<DeviceMessageResultVO>> getDeviceMessageByRegion(String name,  List<String> indexCode, String[] regionCode , Map<String, String> regionMap, String type);


    /**
     * 获取区域
     * @return  返回设备信息
     */
    List<DeviceMessageResultVO.ChannelMessage> getChannelMessage(List<String> deviceIndexCode, List<String> indexCode, String[] regionCode, String type, Map<String, String> regionMap);


    List<DeviceMessageResultVO.ChannelMessage> getChannelMessageByType(List<String> deviceIndexCode);

    Map<String, String> getRegionMessageMap();




}
