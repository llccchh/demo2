package com.hikvision.ga.xpluginmgr.modules.groupc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.*;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkRecordInfoVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.MyLinkRecordListVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.RecordLevByDateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liuyinghao5
 * @date 2022/11/14 14:59
 */
@Mapper
@Component
public interface LinkRecordMapper extends BaseMapper<LinkRecord> {
    /**
     * @author liting43
     * 获取联动记录中的起点点位备通道编码
     */
    @Select("select ap.start_channel_name from public.xp_link_record r join public.xp_link_arming_plan ap on r.arming_plan_id = ap.id")
    List<String> selectChannelName();;

    /**
     * @author liting43
     * 获取联动记录中的分组名称信息
     */
    @Select("select group_name \n" +
            "from public.xp_link_arming_plan ap, public.xp_link_record r, public.xp_link_group g\n" +
            "where ap.id = r.arming_plan_id and ap.group_id = g.id")
    List<String> selectGroupName();

    /**
     * @author liting43
     * 获取联动记录中的触发事件信息
     */
    @Select("select event_name \n" +
            "from public.xp_link_arming_plan ap, public.xp_link_record r, public.xp_link_event e\n" +
            "where ap.id = r.arming_plan_id and ap.event_id = e.id")
    List<String> selectEventName();

    /**
     * 根据时间查询时间段内的等级分级
     *
     * @param now 今天的日期
     * @param pass 过去七天的日期
     * @return 返回事件等级
     */
    @Select("select ag.event_level as lev,  TO_CHAR(ag.create_time, 'YYYY-MM-DD') as date from xp_link_record xlr left join xp_link_arming_plan ag on xlr.arming_plan_id = ag.id where #{pass} < xlr.event_happen_time and xlr.event_happen_time < #{now}")
    List<RecordLevByDateVO> getRecordLevByDate(@Param("now") Date now, @Param("pass") Date pass);

    /**
     * 根据时间查询时间段内的等级分级
     *
     * @param now 今天的日期
     * @param pass 过去7个月的时间
     * @return 返回事件等级
     */
    @Select("select ag.event_level as lev,  TO_CHAR(ag.create_time, 'YYYY-MM') as date from xp_link_record xlr left join xp_link_arming_plan ag on xlr.arming_plan_id = ag.id where #{pass} < xlr.event_happen_time and xlr.event_happen_time < #{now}")
    List<RecordLevByDateVO> getRecordLevByYearDate(@Param("now") Date now, @Param("pass") Date pass);

    List<MyLinkRecordListVO> selectRecordsList(@Param("startName") String startName, @Param("groupName") String groupName, @Param("eventType") String eventType, @Param("eventLevel") Integer eventLevel, @Param("checkState") Integer confirmInfo);

    /**
     * @author liting43
     * 获取联动记录信息
     */
    @Select("select r.id, e.event_name, e.event_type, ap.event_level, ap.start_channel_name, ap.start_channel_code, g.group_name, \n" +
            "r.event_happen_time, r.confirm_status, r.confirm_info, r.confirm_opinion, r.confirm_time \n" +
            "from public.xp_link_record r, public.xp_link_arming_plan ap, public.xp_link_event e, public.xp_link_group g\n" +
            "where r.arming_plan_id = ap.id and ap.event_id = e.id and ap.group_id = g.id and r.id = #{recordId}")
    LinkRecordInfoVO getRecordInfo(Integer recordId);

    /**
     * @author liting43
     * 获取联动记录中的动作信息
     */
    @Select("select a.id, a.action_name, a.link_channel_name, a.link_channel_code, a.action_param, a.arming_plan_id, a.execute_sequence, a.create_time \n" +
            "from public.xp_link_arming_plan ap, public.xp_link_record r, public.xp_link_action a\n" +
            "where ap.id = r.arming_plan_id and ap.id = a.arming_plan_id and r.id = #{recordId} order by a.execute_sequence")
    List<LinkAction> selectLinkAction(Integer recordId);

    /**
     * @author liting43
     * 获取联动记录中的图片URL
     */
    @Select("select ci.capture_url \n" +
            "from public.xp_link_record r, public.xp_link_capture_info ci\n" +
            "where r.id = ci.record_id and r.id = #{recordId}")
    List<String> selectCaptureInfo(Integer recordId);


    @Select("select count(*) from xp_link_record")
    Integer getCount();
}
