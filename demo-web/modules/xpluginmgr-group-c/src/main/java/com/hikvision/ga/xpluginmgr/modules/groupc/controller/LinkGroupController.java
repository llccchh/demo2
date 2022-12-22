package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.AddLinkGroupDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.BatchIdDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.DeviceDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.GroupSearchDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkGroupService;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author liting43、lichanghao6
 */
@RestController
@RequestMapping("/cameraLink/v1/linkGroupManage")
public class LinkGroupController {

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(LinkGroupController.class);

    @Resource
    LinkGroupService linkGroupService;


    /**
     * @author liting43
     * 添加、编辑联动分组
     */
    @PostMapping("/saveLinkGroup.do")
    public BaseResult saveLinkGroup(@RequestBody AddLinkGroupDTO addLinkGroupDTO){
        BaseResult result = linkGroupService.saveLinkGroup(addLinkGroupDTO);
        return result.isSuccess() ? BaseResult.success("Save link group success.") : result;
    }

    /**
     * @author liting43
     * 批量删除联动分组
     */
    @PostMapping("/delLinkGroup.do")
    public BaseResult delLinkGroup(@RequestBody BatchIdDTO batchIdDTO){
        return linkGroupService.delLinkGroup(batchIdDTO);
    }

    /**
     * @author lichanghao6
     * 获取分组列表
     */
    @PostMapping("/getGroupList.do")
    public BaseResult getGroupList(@RequestBody GroupSearchDTO groupSearchDTO) {
        return linkGroupService.getGroupList(groupSearchDTO);

    }

    /**
     * @author lichanghao6
     * 获取分组详情
     */
    @GetMapping("/getGroupInfo.do")
    public BaseResult getGroupInfo(@Param("groupId")  Integer groupId) {
        return linkGroupService.getGroupInfo(groupId);
    }

    /**
     * @author lichanghao6
     * 获取分组设备列表
     */
    @PostMapping("/getDeviceList.do")
    public BaseResult getGroupInfo(@RequestBody DeviceDTO deviceDTO) {
        return linkGroupService.getGroupDeviceList(deviceDTO);
    }


    /**
     * @author liting43
     * 修改联动分组名称或描述
     */
    @GetMapping("/save.do")
    public BaseResult save(@Param("id") Integer id, @Param("name") String name, @Param("description") String description){
        if(StringUtils.isEmpty(name) && StringUtils.isEmpty(description)){
            return BaseResult.success(null);
        }
        return linkGroupService.save(id, name, description);
    }


}

