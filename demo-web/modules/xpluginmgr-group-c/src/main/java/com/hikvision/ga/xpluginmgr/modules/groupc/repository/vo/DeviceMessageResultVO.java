package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liuyinghao5
 * @date 2022/11/21 13:43
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceMessageResultVO {
    private String latitude;
    private String deviceName;
    /**
     * 纬度
     */
    private String longitude;
    private String deviceCode;
    private String state;
    private String deviceType;
    private String region;

    private List<ChannelMessage> channelInfo;




    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChannelMessage {
        private String channelName;
        private String channelCode;

        private String channelType;

        private String state;
        /**
         * 所属设备名字
         */
        private String belongDeviceName;
        private String belongDeviceCode;
        private String belongRegion;


        public void changeToMap(Map<String, List<ChannelMessage>> map, Map<String, String> deviceMap) {
            List<ChannelMessage> list = map.get(belongDeviceCode);
            if (list == null) {
                list = new ArrayList<>();
                list.add(this);
                map.put(belongDeviceCode, list);
            }
            else {
                list.add(this);
            }

            this.belongDeviceName = deviceMap.get(belongDeviceCode);
        }

        /**
         * 链表键值对转pojo
         *
         * @param json xres查到的数据
         * @param map 区域检索map
         * @return pojo
         */
        public  ChannelMessage map2RegionResourceVO(JSONObject json, Map<String, String> map) {


            JSONArray resourceOrg = json.getJSONArray("resourceOrg");
            String treeCode = resourceOrg.getJSONObject(0).getString("indexCode");
            this.setBelongRegion(map.get(treeCode));
            this.setBelongDeviceCode(json.getString("deviceIndexCode"));
            this.setChannelCode(json.getString("indexCode"));
            this.setChannelName(json.getString("name"));
            this.setChannelType(json.getString("resourceType"));

            return this;
        }


        public void setStatus(Map<Object, Object> map) {
            state = map.get(channelCode) == null ? "未检测" : "0".equals(map.get(channelCode)) ? "离线" : "在线";
        }

    }






    public static DeviceMessageResultVO map2DeviceResourceVO(JSONObject linkedHashMap) {
        JSONArray resourceOrg = linkedHashMap.getJSONArray("resourceOrg");
        String treeCode = resourceOrg.getJSONObject(0).getString("indexCode");
        DeviceMessageResultVO deviceMessageResultVO = new DeviceMessageResultVO();
        deviceMessageResultVO.setDeviceCode(linkedHashMap.getString("indexCode"));
        deviceMessageResultVO.setLatitude(linkedHashMap.getString("latitude"));
        deviceMessageResultVO.setDeviceName(linkedHashMap.getString("name"));
        deviceMessageResultVO.setLongitude(linkedHashMap.getString("longitude"));
        deviceMessageResultVO.setDeviceType(linkedHashMap.getString("resourceType"));
        deviceMessageResultVO.setRegion(treeCode);


        return deviceMessageResultVO;
    }


    public void setStatus(Map<Object, Object> map) {
        state = map.get(deviceCode) == null ? "未检测" : "0".equals(map.get(deviceCode)) ? "离线" : "在线";
    }


}
