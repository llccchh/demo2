package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liting43
 * @date 2022/11/25 11:10
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArmingPlanGetDeviceVO {
    private String deviceName;
    private String deviceIndexCode;
    private String deviceType;
    private String region;
    private String longitude;
    private String latitude;
    private String deviceStatus;
    private List<String> eventCapability;
    private List<String> actionCapability;
    private List<ChannelInfo> channelInfo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChannelInfo{
        private String channelName;
        private String channelIndexCode;
        private String channelType;
        private String deviceCode;
        private String region;

        public static ChannelInfo getChannelData(DeviceMessageResultVO.ChannelMessage channelMessage){
            ChannelInfo channelInfo = new ChannelInfo();
            channelInfo.setChannelName(channelMessage.getChannelName());
            channelInfo.setChannelIndexCode(channelMessage.getChannelCode());
            channelInfo.setChannelType(channelMessage.getChannelType());
            channelInfo.setDeviceCode(channelMessage.getBelongDeviceCode());
            channelInfo.setRegion(channelMessage.getBelongRegion());
            return channelInfo;
        }
    }

    public static ArmingPlanGetDeviceVO getDeviceData(DeviceMessageResultVO deviceMessageResultVO){
        ArmingPlanGetDeviceVO armingPlanGetDeviceVO = new ArmingPlanGetDeviceVO();
        armingPlanGetDeviceVO.setDeviceName(deviceMessageResultVO.getDeviceName());
        armingPlanGetDeviceVO.setDeviceIndexCode(deviceMessageResultVO.getDeviceCode());
        armingPlanGetDeviceVO.setDeviceType(deviceMessageResultVO.getDeviceType());
        armingPlanGetDeviceVO.setRegion(deviceMessageResultVO.getRegion());
        armingPlanGetDeviceVO.setLongitude(deviceMessageResultVO.getLongitude());
        armingPlanGetDeviceVO.setLatitude(deviceMessageResultVO.getLatitude());
        armingPlanGetDeviceVO.setDeviceStatus(deviceMessageResultVO.getState());
        armingPlanGetDeviceVO.setChannelInfo(deviceMessageResultVO.getChannelInfo().parallelStream().map(ArmingPlanGetDeviceVO.ChannelInfo::getChannelData).collect(Collectors.toList()));
        return armingPlanGetDeviceVO;
    }
}
