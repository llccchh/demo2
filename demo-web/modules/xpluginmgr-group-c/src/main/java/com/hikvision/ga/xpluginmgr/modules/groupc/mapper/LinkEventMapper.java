package com.hikvision.ga.xpluginmgr.modules.groupc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkEvent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/14 12:43
 * @description
 */
@Mapper
public interface LinkEventMapper extends BaseMapper<LinkEvent> {

    @Select("select event_Type_code from public.xp_link_event where id = #{id}")
    Integer getEventTypeCode(Integer id);
}
