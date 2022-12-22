package com.hikvision.ga.xpluginmgr.modules.groupc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ReGroupDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/7 16:59
 * @description
 */

@Mapper
public interface ReGroupDeviceMapper extends BaseMapper<ReGroupDevice> {

    /**
     * @author liting43
     * 根据分组ID批量删除通道数据
     *
     * @param ids groupIdList
     * @return void
     */
    Integer deleteChannelInGroupId(List<Integer> ids);

    /**
     * @author liting43
     * 根据分组ID查询设备编码
     *
     * @param id groupId
     * @return void
     */
    @Select("select device_index_code from public.xp_link_r_group_device where group_id = #{id}")
    List<String> selectDeviceCodeByGroupId(Integer id);

    /**
     * @author liting43
     * 根据分组ID查询通道编码
     *
     * @param id groupId
     * @return void
     */
    @Select("select channel_code from public.xp_link_r_group_device where group_id = #{id}")
    List<String> selectChannelCodeByGroupId(Integer id);
}
