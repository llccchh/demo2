package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.FindChannelByDeviceDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.ChannelMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.DeviceMessageVO;

import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/9 14:35
 */

public interface DeviceService {

     BaseResult<List<ChannelMessageVO>> getChannelMessage(FindChannelByDeviceDTO findChannelByDeviceDTO);


     /**
      * @param list 设备编码
      * @return 返回通道列表
      */
     List<ChannelMessageVO> getChannelByDeviceIndexCode(List<String> list);

     /**
      * 根据通道编码返回通道详情
      * @param list 通道编码list
      * @return 通道详情
      */
     BaseResult<List<ChannelMessageVO>> getChannelMessageNyIndexCode(List<String> list);


     /**
      * 根据通道编码列表返回对应的事件能力集
      * @param indexCode 通道编码
      * @return 事件列表
      */
     String getChannelEvent(String indexCode);

     /**
      * 获取所有设备信息，包括名称和编码
      *
      *
      */
     List<DeviceMessageVO> getDevice(String type);

}