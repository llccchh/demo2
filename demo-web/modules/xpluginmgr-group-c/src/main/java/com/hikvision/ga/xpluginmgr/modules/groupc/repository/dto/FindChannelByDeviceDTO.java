package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import java.util.Arrays;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/9 15:31
 */

public class FindChannelByDeviceDTO {

    /**
     * 资源类型
     */
    private String resourceType;
    /**
     * 第几页
     */
    private Integer pageNo;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 查询规则
     */
    private Map<String, String[]> exactCondition;
    /**
     *
     */
    private String[] fields;

    public FindChannelByDeviceDTO(String resourceType, Integer pageNo, Integer pageSize, Map<String, String[]> exactCondition, String[] fields) {
        this.resourceType = resourceType;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.exactCondition = exactCondition;
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "FindChannelByDeviceDTO{" +
                "resourceType='" + resourceType + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", exactCondition=" + exactCondition +
                ", fields=" + Arrays.toString(fields) +
                '}';
    }

    public FindChannelByDeviceDTO() {
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
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

    public Map<String, String[]> getExactCondition() {
        return exactCondition;
    }

    public void setExactCondition(Map<String, String[]> exactCondition) {
        this.exactCondition = exactCondition;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }
}
