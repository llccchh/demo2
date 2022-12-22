package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.XresRegionMessageVO;

import java.util.List;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/4 14:40
 */

public interface XresService {


    /**
     * 更新区域信息，此处监听了mq队列
     * @return 返回 列表
     */
    BaseResult<List<XresRegionMessageVO>> getRegionIndexCode();


    /**
     * 根据区域信息查询区域下的资源
     * @param param 目录编码
     * @return 返回区域资源信息
     */
    BaseResult<Map<String, Object>> getResourceByIndexCode(Object param);


    BaseResult<Map<String, Object>> getAllResourceByType(String type);



}
