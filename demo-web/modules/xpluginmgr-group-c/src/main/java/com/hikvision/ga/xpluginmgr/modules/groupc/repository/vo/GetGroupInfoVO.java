package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lichanghao6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetGroupInfoVO {

    private Integer id;
    private String groupName;
    private String description;
    private List<ChannelMessageVO> channelMessageList;

}
