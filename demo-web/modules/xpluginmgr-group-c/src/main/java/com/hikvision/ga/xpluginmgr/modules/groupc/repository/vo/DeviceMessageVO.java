package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * @author liuyinghao5
 * @date 2022/11/18 19:29
 */

public class DeviceMessageVO {

    private String deviceIndexCode;
    private String name;

    public DeviceMessageVO(String deviceIndexCode, String name) {
        this.deviceIndexCode = deviceIndexCode;
        this.name = name;
    }

    public DeviceMessageVO() {
    }

    public DeviceMessageVO(JSONObject jsonObject) {
        this.deviceIndexCode = jsonObject.getString("indexCode");
        this.name = jsonObject.getString("name");
    }

    public String getDeviceIndexCode() {
        return deviceIndexCode;
    }

    public void setDeviceIndexCode(String deviceIndexCode) {
        this.deviceIndexCode = deviceIndexCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeviceMessageVO{" +
                "deviceIndexCode='" + deviceIndexCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
