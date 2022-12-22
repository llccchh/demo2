package com.hikvision.ga.xpluginmgr.modules.groupc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkAction;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/9 15:40
 * @description 执行动作mapper
 */
@Mapper
public interface LinkActionMapper extends BaseMapper<LinkAction> {

    /**
     * @author liting43
     * 根据规则ID批量查询通道编码
     *
     * @param armingPlanId armingPlanId
     * @return list
     */
    List<String> selectChannelCodeByBatchApId(List<Integer> armingPlanId);

    /**
     * @author liting43
     * 根据规则ID和通道编码批量查询数据
     *
     * @param armingPlanId 规则ID
     * @param delChannelCode 联动设备通道编码
     * @return 动作信息
     */
    List<Integer> selectByBatchApIdAndChannelCode(@Param("armingPlanId") List<Integer> armingPlanId, @Param("delChannelCode") List<String> delChannelCode);

    @Select("select action_name from xp_link_action xla where xla.arming_plan_id = #{armingId} order by arming_plan_id")
    List<String> getOrderOperateName(Integer armingId);


    /**
     * 根据
     *
     * @param indexCode  设备编码
     * @param eventType  事件类型
     * @return 操作所需参数
     */
    @Select("select xla.action_name as name,  xla.link_channel_code as indexCode, xla.action_param as jsonObject, xla.arming_plan_id as armingPlanId from xp_link_action as xla left join xp_link_arming_plan xlap on xla.arming_plan_id = xlap.id left join  xp_link_event as xle on xle.id = xlap.event_id where xle.event_type_code = #{eventType} and xlap.start_channel_code = #{indexCode} and xlap.data_state = 0 and xlap.arming_plan_status = 1 order by xla.execute_sequence  ")
    List<LinkActionVO> getActionBySrcIndexCodeAndEventType(String indexCode, Integer eventType);




}
