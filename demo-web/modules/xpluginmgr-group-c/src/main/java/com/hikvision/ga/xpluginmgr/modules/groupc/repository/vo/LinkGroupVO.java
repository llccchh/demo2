package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author lichanghao6
 */
public class LinkGroupVO {

    private Integer totalNum;
    private List<LinkGroupList> linkGroupList;

    public LinkGroupVO(Integer totalNum, List<LinkGroupList> linkGroupList) {
        this.totalNum = totalNum;
        this.linkGroupList = linkGroupList;
    }

    public LinkGroupVO() {
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public List<LinkGroupList> getLinkGroupList() {
        return linkGroupList;
    }

    public void setLinkGroupList(List<LinkGroupList> linkGroupList) {
        this.linkGroupList = linkGroupList;
    }

    @Override
    public String toString() {
        return "LinkGroupVO{" +
                "totalNum=" + totalNum +
                ", dataList=" + linkGroupList +
                '}';
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LinkGroupList {

        private Integer id;
        private String groupName;
        private String description;
        private String createUser;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
        private Date createTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
        private Date updateTime;
        private Integer armingPlanCount;
        /**
         * 分组是否可以删除
         */
        private Boolean isDeleteAbel;

    }
}
