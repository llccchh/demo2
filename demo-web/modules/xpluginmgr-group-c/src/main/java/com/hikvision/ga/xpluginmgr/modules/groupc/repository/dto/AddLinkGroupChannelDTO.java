package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ReGroupDevice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liting43
 * @date 2022/11/8 15:23
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLinkGroupChannelDTO {
    private String channelCode;
    private String deviceIndexCode;

    public static AddLinkGroupChannelDTO getData(ReGroupDevice reGroupDevice){
        AddLinkGroupChannelDTO addLinkGroupChannelDTO = new AddLinkGroupChannelDTO();
        addLinkGroupChannelDTO.setChannelCode(reGroupDevice.getChannelCode());
        addLinkGroupChannelDTO.setDeviceIndexCode(reGroupDevice.getDeviceIndexCode());
        return addLinkGroupChannelDTO;
    }
}
