package com.hikvision.ga.xpluginmgr.modules.groupc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.TimeStrategy;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.GetTimeStrategyTemplateDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.TimeStrategyMessageVO;
import org.apache.ibatis.annotations.*;

/**
 * @author liuyinghao5
 * @date 2022/11/10 11:22
 */

@Mapper
public interface TimeStrategyMapper extends BaseMapper<TimeStrategy> {

    /**
     * 从模板中导入数据到策略表
     *
     * @param dto 模板id
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into xp_link_time_strategy  (monday, tuesday, wednesday, thursday, friday, saturday, sunday)   select  tts.monday, tts.tuesday, tts.wednesday, tts.thursday, tts.friday, tts.saturday, tts.sunday from xp_link_temp_time_strategy as tts where tts.id =  #{templateId}")
    void insertDataByTemplate(GetTimeStrategyTemplateDTO dto);

    /**
     * 通过布撤防id查询对应的时间策略信息
     *
     * @param id 布撤防id
     * @return 返回模板中的信息 具体见vo
     */
    @Select(value = "select tts.id, tts.monday, tts.tuesday, tts.wednesday, tts.thursday, tts.friday, tts.saturday, tts.sunday from xp_link_time_strategy as tts left join xp_link_arming_plan as ap on ap.time_temp_id = tts.id where ap.id = #{id}")
    TimeStrategyMessageVO getMessageByArmingPlanId(@Param("id") Integer id);


}
