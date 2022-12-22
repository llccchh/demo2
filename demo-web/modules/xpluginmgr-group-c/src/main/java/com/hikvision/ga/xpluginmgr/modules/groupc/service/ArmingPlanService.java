package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.AddArmingPlanDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ArmingPlanSearchDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.AddLinkGroupChannelDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.SaveArmingPlanDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ArmingPlan;
import com.hikvision.hik.crypt.CryptErrorException;

import java.util.List;


public interface ArmingPlanService extends IService<ArmingPlan> {

    /**
     * @author liting43
     * 根据分组ID获取联动规则
     *
     * @param ids 分组ID列表
     * @return 联动规则
     */
    List<ArmingPlan> selectByGroupIds(List<Integer> ids);

    /**
     * @author liting43
     * 根据规则ID批量删除布撤防（联动规则）
     *
     * @param ids 规则ID列表
     * @return void
     */
    BaseResult<Object> delArmingPlan(List<Integer> ids);

    /**
     * @author lichanghao6
     * 根据分组ID获取联动规则列表
     *
     * @param armingPlanSearchDTO 页码、页面大小、分组id、规则状态、规则名称
     * @return 联动规则列表
     */
    BaseResult getArmingPlanList(ArmingPlanSearchDTO armingPlanSearchDTO);

    /**
     * @author lichanghao6
     * 根据联动规则ID获取联动规则详情
     *
     * @param id 规则ID
     * @return 联动规则详情
     */
    BaseResult getArmingPlanInfo(Integer id);

    /**
     * @author liuyinghao5
     * 添加联动规则
     *
     * @param addArmingPlanDTO 添加的信息
     * @return 返回新增后的id
     */
    BaseResult<Object> addArmingPlan(AddArmingPlanDTO addArmingPlanDTO);

    /**
     * @author liting43
     * 编辑联动规则
     *
     * @param saveArmingPlanDTO 添加的信息
     * @return 返回新增后的id
     */
    BaseResult<Object> updateArmingPlan(SaveArmingPlanDTO saveArmingPlanDTO);

    /**
     * @author liting43
     * 启用 / 禁用规则（布防）
     *
     * @param id 规则ID
     * @return BaseResult
     */
    BaseResult<Object> enableArmingPlan(Integer id) throws CryptErrorException ;

    /**
     * @author liting43
     * 批量启用 / 禁用规则（布防）
     *
     * @param ids 规则ID
     * @return BaseResult
     */
    BaseResult<Object> enableArmingPlanBatch(Integer status, List<Integer> ids) throws CryptErrorException;

    /**
     * @author liting43
     * 获取添加规则时展示的当前分组内的设备信息
     *
     * @param groupId 分组ID
     * @param selectInfo 查询信息
     * @param deviceType 设备类型
     * @return 添加规则所属分组的设备和通道信息
     */
    BaseResult<Object> getDevice(Integer groupId, String selectInfo, String deviceType);


    /**
     *
     * 根据时间模板id查找对应状态的id
     *
     * @param timeTemId 事件模板id
     * @param state 状态
     * @return 返回id列表
     */
   List<Integer> getMessageByTime(Integer timeTemId, Integer state);

    Integer getArmingPlanCount(Integer id);

    Boolean getArmingPlanState(Integer groupId);

    Boolean isNotRelateArmingPlan(Integer groupId, List<AddLinkGroupChannelDTO> delChannelDTOList);

    List<Integer> getArmingPlanId(Integer groupId);
}
