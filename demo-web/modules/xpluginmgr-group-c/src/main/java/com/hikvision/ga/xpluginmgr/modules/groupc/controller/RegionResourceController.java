package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.FindResourceDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.GetResourceByRegionDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.ChannelMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.DeviceMessageResultVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.RegionVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.RegionResourceService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.XresService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/8 10:54
 */

@Controller
@RequestMapping("cameraLink/xres/ui/region/message")
public class RegionResourceController {

    @Resource
    private XresService xresService;

    @Resource
    private RegionResourceService regionResourceService;


    /**
     * 获取区域信息
     */
    @ResponseBody
    @RequestMapping("getRegionResource.do")
    public BaseResult<List<RegionVO>> getRegion(String name) {
        return regionResourceService.getRootIndexCode(name);
    }

    /**
     * 根据区域编码查询区域下的资源
     */
    @ResponseBody
    @RequestMapping(value = "getResourceByRegion.do", method = RequestMethod.POST)
    public BaseResult<List<DeviceMessageResultVO>> getResourceByRegion(@RequestBody GetResourceByRegionDTO getResourceByRegionDTO) {

        return regionResourceService.getDeviceMessageByRegion(getResourceByRegionDTO);
    }

}