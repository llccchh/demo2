package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/11 17:09
 */

public class DeviceIfOnlineDTO {

    private List<String> indexCodes;

    private Integer pageNo;

    private Integer pageSize;

    private String resourceType;

    public DeviceIfOnlineDTO(List<String> indexCodes, Integer pageNo, Integer pageSize, String resourceType) {
        this.indexCodes = indexCodes;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.resourceType = resourceType;
    }

    public DeviceIfOnlineDTO() {
    }

    public DeviceIfOnlineDTO(List<String> list){
        indexCodes = list;
        pageNo = 1;
        pageSize = list.size() == 0 ? 50 : list.size();
        resourceType = "camera";
    }

    @Override
    public String toString() {
        return "DeviceIfOnlineDTO{" +
                "indexCodes=" + indexCodes +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }

    public List<String> getIndexCodes() {
        return indexCodes;
    }

    public void setIndexCodes(List<String> indexCodes) {
        this.indexCodes = indexCodes;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
