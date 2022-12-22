package com.hikvision.ga.xpluginmgr.modules.groupc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.TimeStrategyTemplate;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.TimeStrategyTemplateMessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/10 10:26
 */


@Mapper
public interface TimeStrategyTemplateMapper extends BaseMapper<TimeStrategyTemplate> {

    /**
     * 返回模板中有效参数列表
     * @return 返回模板中的信息 具体见vo
     */
    @Select(value = "select tts.id, tts.temp_name, tts.monday, tts.tuesday, tts.wednesday, tts.thursday, tts.friday, tts.saturday, tts.sunday from xp_link_temp_time_strategy as tts")
    List<TimeStrategyTemplateMessageVO> getAllMessage();




    /**
     * 通过布撤防id查询对应的时间策略模板
     * @return 返回模板中的信息 具体见vo
     * @param id 布撤防id
     */
    @Select(value = "select tts.id, tts.temp_name, tts.monday, tts.tuesday, tts.wednesday, tts.thursday, tts.friday, tts.saturday, tts.sunday from xp_link_temp_time_strategy as tts left join xp_link_arming_plan as ap on ap.time_temp_id = tts.id where ap.id = #{id}   ")
    TimeStrategyTemplateMessageVO getMessageByArmingPlanId(@Param("id") Integer id);


}
