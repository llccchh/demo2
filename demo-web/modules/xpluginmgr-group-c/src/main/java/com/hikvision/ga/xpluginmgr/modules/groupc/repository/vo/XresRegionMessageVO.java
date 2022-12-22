package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;


import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/8 11:33
 */

// todo 记得加注释

public class XresRegionMessageVO {
    private Integer cascadeType;
    private Integer localQuantity;
    private String cascadeCode;
    private String indexCode;
    private Integer sort;
    private Boolean leaf;
    private Integer totalOnlineQuantity;
    private Integer orgType;
    private Integer totalQuantity;
    private String tagPath;
    private String name;
    private String tag;
    private String parentIndexCode;
    private String treePath;
    // 本区域在线资源数量，只统计本级挂的资源数量，不包含下级及下下级等；
    private Integer localOnlineQuantity;
    // 国标码
    private String externalIndexCode;
    // 父国标码
    private String parentExternalIndexCode;

    public XresRegionMessageVO(Integer cascadeType, Integer localQuantity, String cascadeCode, String indexCode, Integer sort, Boolean leaf, Integer totalOnlineQuantity, Integer orgType, Integer totalQuantity, String tagPath, String name, String tag, String parentIndexCode, String treePath, Integer localOnlineQuantity, String externalIndexCode, String parentExternalIndexCode) {
        this.cascadeType = cascadeType;
        this.localQuantity = localQuantity;
        this.cascadeCode = cascadeCode;
        this.indexCode = indexCode;
        this.sort = sort;
        this.leaf = leaf;
        this.totalOnlineQuantity = totalOnlineQuantity;
        this.orgType = orgType;
        this.totalQuantity = totalQuantity;
        this.tagPath = tagPath;
        this.name = name;
        this.tag = tag;
        this.parentIndexCode = parentIndexCode;
        this.treePath = treePath;
        this.localOnlineQuantity = localOnlineQuantity;
        this.externalIndexCode = externalIndexCode;
        this.parentExternalIndexCode = parentExternalIndexCode;
    }

    public XresRegionMessageVO() {
    }

    public Integer getCascadeType() {
        return cascadeType;
    }

    public void setCascadeType(Integer cascadeType) {
        this.cascadeType = cascadeType;
    }

    public Integer getLocalQuantity() {
        return localQuantity;
    }

    public void setLocalQuantity(Integer localQuantity) {
        this.localQuantity = localQuantity;
    }

    public String getCascadeCode() {
        return cascadeCode;
    }

    public void setCascadeCode(String cascadeCode) {
        this.cascadeCode = cascadeCode;
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

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Integer getTotalOnlineQuantity() {
        return totalOnlineQuantity;
    }

    public void setTotalOnlineQuantity(Integer totalOnlineQuantity) {
        this.totalOnlineQuantity = totalOnlineQuantity;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getTagPath() {
        return tagPath;
    }

    public void setTagPath(String tagPath) {
        this.tagPath = tagPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getParentIndexCode() {
        return parentIndexCode;
    }

    public void setParentIndexCode(String parentIndexCode) {
        this.parentIndexCode = parentIndexCode;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public Integer getLocalOnlineQuantity() {
        return localOnlineQuantity;
    }

    public void setLocalOnlineQuantity(Integer localOnlineQuantity) {
        this.localOnlineQuantity = localOnlineQuantity;
    }

    public String getExternalIndexCode() {
        return externalIndexCode;
    }

    public void setExternalIndexCode(String externalIndexCode) {
        this.externalIndexCode = externalIndexCode;
    }

    public String getParentExternalIndexCode() {
        return parentExternalIndexCode;
    }

    public void setParentExternalIndexCode(String parentExternalIndexCode) {
        this.parentExternalIndexCode = parentExternalIndexCode;
    }

    @Override
    public String toString() {
        return "XresRegionMessageVO{" +
                "cascadeType=" + cascadeType +
                ", localQuantity=" + localQuantity +
                ", cascadeCode='" + cascadeCode + '\'' +
                ", indexCode='" + indexCode + '\'' +
                ", sort=" + sort +
                ", leaf=" + leaf +
                ", totalOnlineQuantity=" + totalOnlineQuantity +
                ", orgType=" + orgType +
                ", totalQuantity=" + totalQuantity +
                ", tagPath='" + tagPath + '\'' +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", parentIndexCode='" + parentIndexCode + '\'' +
                ", treePath='" + treePath + '\'' +
                ", localOnlineQuantity=" + localOnlineQuantity +
                ", externalIndexCode='" + externalIndexCode + '\'' +
                ", parentExternalIndexCode='" + parentExternalIndexCode + '\'' +
                '}';
    }

    public static XresRegionMessageVO linkedHashMap2Vo(JSONObject linkedHashMap){
        XresRegionMessageVO xresRegionMessageVO = new XresRegionMessageVO();
        xresRegionMessageVO.setName((String) linkedHashMap.get("name"));
        xresRegionMessageVO.setIndexCode((String) linkedHashMap.get("indexCode"));
        xresRegionMessageVO.setParentIndexCode((String) linkedHashMap.get("parentIndexCode"));
        xresRegionMessageVO.setCascadeCode((String) linkedHashMap.get("cascadeCode"));
        xresRegionMessageVO.setCascadeType((Integer) linkedHashMap.get("cascadeType"));
        xresRegionMessageVO.setOrgType((Integer) linkedHashMap.get("orgType"));
        xresRegionMessageVO.setParentExternalIndexCode((String) linkedHashMap.get("parentExternalIndexCode"));
        xresRegionMessageVO.setTreePath((String) linkedHashMap.get("treePath"));
        return xresRegionMessageVO;

    }

}
