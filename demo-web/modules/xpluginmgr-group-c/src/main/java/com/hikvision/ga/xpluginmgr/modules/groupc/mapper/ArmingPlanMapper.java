package com.hikvision.ga.xpluginmgr.modules.groupc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ArmingPlan;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.ArmingPlanInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author liting43
 * @date 2022/11/8 17:26
 * @description
 */
@Mapper
public interface ArmingPlanMapper extends BaseMapper<ArmingPlan> {

    /**
     * @author lichanghao6
     * 根据规则ID获得联动规则详情
     *
     * @param id armingPlanId
     * @return armingPlanVO
     */
    @Select("select ap.arming_plan_name, ap.group_id, ap.start_channel_name, ap.start_channel_code, ap.event_level, ap.description, " +
            "e.event_name, e.event_code, e.event_identity, e.event_type, e.application, tts.temp_name \n" +
            "from public.xp_link_arming_plan ap, public.xp_link_event e, public.xp_link_temp_time_strategy tts\n" +
            "where ap.event_id = e.id and ap.time_temp_id = tts.id and ap.id = #{id}")
    ArmingPlanInfoVO getArmingPlanInfo(Integer id);

    @Select("select arming_plan_status from public.xp_link_arming_plan where id = #{id}")
    Integer selectStatusById(Integer id);

    @Update("update public.xp_link_arming_plan set arming_plan_status = #{status} where id = #{id}")
    void updateArmingPlanStatus(Integer id, Integer status);

}
