package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liuyinghao5
 * @date 2022/11/8 20:06
 */

public class ChannelMessageVO {

    private List<ResourceOrg> resourceOrg;
    private String cascadeCode;
    private String latitude;
    private String name;
    private String channelCode;
    private String recordLocation;
    private Integer cameraType;
    private String longitude;
    private String intelligentSet;
    private String[] capabilitySet;
    private String belongDeviceName;
    private String deviceIndexCode;
    private String Status;

    public void changeToMap(Map<String, List<ChannelMessageVO>> map, Map<String, String> deviceMap) {
        List<ChannelMessageVO> list = map.get(deviceIndexCode);
        if (list == null) {
            list = new ArrayList<>();
            list.add(this);
            map.put(deviceIndexCode, list);
        }
        else {
            list.add(this);
        }

        this.belongDeviceName = deviceMap.get(deviceIndexCode);
    }

    @Override
    public String toString() {
        return "ChannelMessageVO{" +
                "resourceOrg=" + resourceOrg +
                ", cascadeCode='" + cascadeCode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", name='" + name + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", recordLocation='" + recordLocation + '\'' +
                ", cameraType=" + cameraType +
                ", longitude='" + longitude + '\'' +
                ", intelligentSet='" + intelligentSet + '\'' +
                ", capabilitySet=" + Arrays.toString(capabilitySet) +
                ", belongDeviceName='" + belongDeviceName + '\'' +
                ", deviceIndexCode='" + deviceIndexCode + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }

    public ChannelMessageVO(List<ResourceOrg> resourceOrg, String cascadeCode, String latitude, String name, String channelCode, String recordLocation, Integer cameraType, String longitude, String intelligentSet, String[] capabilitySet, String belongDeviceName, String deviceIndexCode, String status) {
        this.resourceOrg = resourceOrg;
        this.cascadeCode = cascadeCode;
        this.latitude = latitude;
        this.name = name;
        this.channelCode = channelCode;
        this.recordLocation = recordLocation;
        this.cameraType = cameraType;
        this.longitude = longitude;
        this.intelligentSet = intelligentSet;
        this.capabilitySet = capabilitySet;
        this.belongDeviceName = belongDeviceName;
        this.deviceIndexCode = deviceIndexCode;
        Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public ChannelMessageVO() {
    }

    public List<ResourceOrg> getResourceOrg() {
        return resourceOrg;
    }

    public void setResourceOrg(List<ResourceOrg> resourceOrg) {
        this.resourceOrg = resourceOrg;
    }

    public String getCascadeCode() {
        return cascadeCode;
    }

    public void setCascadeCode(String cascadeCode) {
        this.cascadeCode = cascadeCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getRecordLocation() {
        return recordLocation;
    }

    public void setRecordLocation(String recordLocation) {
        this.recordLocation = recordLocation;
    }

    public Integer getCameraType() {
        return cameraType;
    }

    public void setCameraType(Integer cameraType) {
        this.cameraType = cameraType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getIntelligentSet() {
        return intelligentSet;
    }

    public void setIntelligentSet(String intelligentSet) {
        this.intelligentSet = intelligentSet;
    }

    public String[] getCapabilitySet() {
        return capabilitySet;
    }

    public void setCapabilitySet(String[] capabilitySet) {
        this.capabilitySet = capabilitySet;
    }

    public String getDeviceIndexCode() {
        return deviceIndexCode;
    }

    public void setDeviceIndexCode(String deviceIndexCode) {
        this.deviceIndexCode = deviceIndexCode;
    }

    public String getBelongDeviceName() {
        return belongDeviceName;
    }

    public void setBelongDeviceName(String belongDeviceName) {
        this.belongDeviceName = belongDeviceName;
    }

    static class ResourceOrg {

        public ResourceOrg(String indexCode, Integer sort, String treeCode, String treePath) {
            this.indexCode = indexCode;
            this.sort = sort;
            this.treeCode = treeCode;
            this.treePath = treePath;
        }

        public ResourceOrg() {
        }

        public String getIndexCode() {
            return indexCode;
        }

        public void setIndexCode(String indexCode) {
            this.indexCode = indexCode;
        }

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public String getTreeCode() {
            return treeCode;
        }

        public void setTreeCode(String treeCode) {
            this.treeCode = treeCode;
        }

        public String getTreePath() {
            return treePath;
        }

        public void setTreePath(String treePath) {
            this.treePath = treePath;
        }

        private String indexCode;
        private Integer sort;
        private String treeCode;
        private String treePath;


        /**
         * 链表键值对转pojo
         *
         * @param linkedHashMap 链表键值对
         * @return pojo
         */
        public static ResourceOrg map2ResourceOrg(JSONObject linkedHashMap) {
            ResourceOrg resourceOrg = new ResourceOrg();
            resourceOrg.setIndexCode((String) linkedHashMap.get("indexCode"));
            resourceOrg.setSort((Integer) linkedHashMap.get("sort"));
            resourceOrg.setTreeCode((String) linkedHashMap.get("treeCode"));
            resourceOrg.setTreePath((String) linkedHashMap.get("treePath"));
            return resourceOrg;
        }
    }

    /**
     * 链表键值对转pojo
     *
     * @param linkedHashMap 链表键值对
     * @return pojo
     */
    @SuppressWarnings("unchecked")
    public static ChannelMessageVO map2RegionResourceVO(JSONObject linkedHashMap) {

        ChannelMessageVO channelMessageVO = new ChannelMessageVO();
        List<JSONObject> linkedHashMaps = (List<JSONObject>) linkedHashMap.get("resourceOrg");
        channelMessageVO.setResourceOrg(linkedHashMaps.stream().map(ResourceOrg::map2ResourceOrg).collect(Collectors.toList()));
        channelMessageVO.setCascadeCode(linkedHashMap.getString("cascadeCode"));
        channelMessageVO.setLatitude(linkedHashMap.getString("latitude"));
        channelMessageVO.setName(linkedHashMap.getString("name"));
        channelMessageVO.setChannelCode(linkedHashMap.getString("channelCode"));
        channelMessageVO.setRecordLocation(linkedHashMap.getString("recordLocation"));
        channelMessageVO.setCameraType(linkedHashMap.getInteger("cameraType"));
        channelMessageVO.setLongitude(linkedHashMap.getString("longitude"));
        channelMessageVO.setIntelligentSet((String) linkedHashMap.get("intelligentSet"));
        channelMessageVO.setCapabilitySet(linkedHashMap.getString("capabilitySet") == null ? null : linkedHashMap.getString("capabilitySet").split("@"));
        channelMessageVO.setDeviceIndexCode((String) linkedHashMap.get("deviceIndexCode"));
        channelMessageVO.setBelongDeviceName((String) linkedHashMap.get("belongDeviceName"));
        return channelMessageVO;
    }

    public void setStatus(Map<Object, Object> map){
        Status = map.get(channelCode) == null ? "未检测" : "0".equals(map.get(channelCode)) ? "离线" : "在线" ;
    }

}
