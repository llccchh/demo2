package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/22 21:16
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfoInGroupVO {
    private String deviceName;
    private String deviceIndexCode;
    private String deviceType;
    private String region;
    private String longitude;
    private String latitude;
    /**
     *  设备是否在线，1-在线，0-离线
     */
    private String deviceStatus;
    private List<String> eventCapability;
    private List<String> actionCapability;
    private List<ChannelInfo> channelInfos;

    public static class ChannelInfo{
        private String channelName;
        private String channelIndexCode;
        private String channelType;
        private String deviceCode;
        private String region;

        public ChannelInfo(String channelName, String channelIndexCode, String channelType, String deviceCode, String region) {
            this.channelName = channelName;
            this.channelIndexCode = channelIndexCode;
            this.channelType = channelType;
            this.deviceCode = deviceCode;
            this.region = region;
        }

        public ChannelInfo() {
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getChannelIndexCode() {
            return channelIndexCode;
        }

        public void setChannelIndexCode(String channelIndexCode) {
            this.channelIndexCode = channelIndexCode;
        }

        public String getChannelType() {
            return channelType;
        }

        public void setChannelType(String channelType) {
            this.channelType = channelType;
        }

        public String getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(String deviceCode) {
            this.deviceCode = deviceCode;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        @Override
        public String toString() {
            return "ChannelInfo{" +
                    "channelName='" + channelName + '\'' +
                    ", channelIndexCode='" + channelIndexCode + '\'' +
                    ", channelType='" + channelType + '\'' +
                    ", deviceCode='" + deviceCode + '\'' +
                    ", region='" + region + '\'' +
                    '}';
        }
    }
}
