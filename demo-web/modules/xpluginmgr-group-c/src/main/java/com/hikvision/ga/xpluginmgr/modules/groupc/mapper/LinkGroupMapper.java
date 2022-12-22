package com.hikvision.ga.xpluginmgr.modules.groupc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkGroup;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.GetGroupInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lichanghao6
 */
@Mapper
public interface LinkGroupMapper extends BaseMapper<LinkGroup> {

    /**
     * 根据groupId查询联动分组内设备编码
     * @param id groupId
     * @return List<String>
     */
    @Select("select b.device_index_code from xp_link_group a left join xp_link_r_group_device b on a.id = b.group_id where a.id = #{id}")
    List<String> getGroupDeviceCodeById(Integer id);

    /**
     * 根据groupId查询联动分组基本信息
     * @param id groupId
     * @return GetGroupInfoVO
     */
    @Select("select id, group_name, description from xp_link_group where id = #{id}")
    GetGroupInfoVO getGroupInfo(Integer id);


}
