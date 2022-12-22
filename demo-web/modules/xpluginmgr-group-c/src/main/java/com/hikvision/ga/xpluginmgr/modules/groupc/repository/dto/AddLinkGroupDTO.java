package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/8 13:49
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLinkGroupDTO {
    private Integer id;
    private String groupName;
    private String description;
    private String createUser;
    private List<AddLinkGroupChannelDTO> channelList;
}
