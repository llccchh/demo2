package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/8 16:19
 */

public class RegionVO {

    private String regionName;
    private String regionIndexCode;
    private String parentIndexCode;


    public RegionVO(String regionName, String regionIndexCode, String parentIndexCode) {
        this.regionName = regionName;
        this.regionIndexCode = regionIndexCode;
        this.parentIndexCode = parentIndexCode;
    }

    public RegionVO() {
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionIndexCode() {
        return regionIndexCode;
    }

    public void setRegionIndexCode(String regionIndexCode) {
        this.regionIndexCode = regionIndexCode;
    }

    public String getParentIndexCode() {
        return parentIndexCode;
    }

    public void setParentIndexCode(String parentIndexCode) {
        this.parentIndexCode = parentIndexCode;
    }

    @Override
    public String toString() {
        return "RegionVO{" +
                "regionName='" + regionName + '\'' +
                ", regionIndexCode='" + regionIndexCode + '\'' +
                ", parentIndexCode='" + parentIndexCode + '\'' +
                '}';
    }


    public static RegionVO stringArray2Pojo(String regionMessage) {
        String[] array = regionMessage.split("@");

        RegionVO regionVO = new RegionVO();
        regionVO.setRegionName(array[0]);
        regionVO.setRegionIndexCode(array[1]);
        regionVO.setParentIndexCode(array[2]);

        return regionVO;
    }
}
