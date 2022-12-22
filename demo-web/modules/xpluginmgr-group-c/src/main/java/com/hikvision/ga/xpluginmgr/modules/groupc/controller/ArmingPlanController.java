package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.*;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.ArmingPlanService;
import com.hikvision.hik.crypt.CryptErrorException;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liuyinghao5
 * @date 2022/11/10 14:49
 */

@RestController
@RequestMapping("/cameraLink/v1/armingPlanManage")
public class ArmingPlanController {

    @Resource
    private ArmingPlanService armingPlanService;

    /**
     * @author lichanghao6
     * 根据联动分组ID获取规则列表
     */
    @PostMapping("/getArmingPlanList.do")
    public BaseResult getArmingPlanList(@RequestBody ArmingPlanSearchDTO armingPlanSearchDTO) {
        return armingPlanService.getArmingPlanList(armingPlanSearchDTO);
    }

    /**
     * @author lichanghao6
     * 根据联动规则ID获取规则详情
     */
    @GetMapping("/getArmingPlanInfo.do")
    public BaseResult getArmingPlanInfo(@RequestParam Integer id) {
        return armingPlanService.getArmingPlanInfo(id);
    }

    /**
     * @author liting43
     * 批量删除布撤防（规则）
     */
    @PostMapping("/delArmingPlan.do")
    public BaseResult<Object> delArmingPlan(@RequestBody BatchIdDTO batchIdDTO){
        return armingPlanService.delArmingPlan(batchIdDTO.getIds());
    }


    /**
     * @author liuyinghao5
     * 添加联动规则
     */
    @PostMapping(value = "addArmingPlan.do")
    public BaseResult<Object> addArmingPlan(@RequestBody AddArmingPlanDTO addArmingPlanDTO){
        return armingPlanService.addArmingPlan(addArmingPlanDTO);
    }

    /**
     * @author liting43
     * 编辑联动规则
     */
    @PostMapping(value = "updateArmingPlan.do")
    public BaseResult<Object> updateArmingPlan(@RequestBody SaveArmingPlanDTO saveArmingPlanDTO){
        return armingPlanService.updateArmingPlan(saveArmingPlanDTO);
    }

    /**
     * @author liting43
     * 启用 / 禁用规则（布防）
     */
    @GetMapping("/enableArmingPlan.do")
    public BaseResult<Object> enableArmingPlan(@Param("id") Integer id) throws CryptErrorException {
        return armingPlanService.enableArmingPlan(id);
    }

    /**
     * @author liting43
     * 批量启用 / 禁用规则（布防）
     */
    @PostMapping("/enableArmingPlanBatch.do")
    public BaseResult<Object> enableArmingPlanBatch(@RequestBody ArmingPlanStatusDTO armingPlanStatusDTO) throws CryptErrorException {
        return armingPlanService.enableArmingPlanBatch(armingPlanStatusDTO.getArmingPlanStatus(), armingPlanStatusDTO.getIds());
    }

    /**
     * @author liting43
     * 获取添加规则时展示的当前分组内的设备信息
     */
    @GetMapping("getDevice.do")
    public BaseResult<Object> getDevice(@Param("groupId") Integer groupId, @Param("selectInfo") String selectInfo, @Param("deviceType") String deviceType){
        return armingPlanService.getDevice(groupId, selectInfo, deviceType);
    }

}
