package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;

/**
 * @author lichanghao6
 */
public class ArmingPlanVO {

    private Integer totalNum;
    private List<ArmingPlanList> armingPlanList;

    public ArmingPlanVO(Integer totalNum, List<ArmingPlanList> armingPlanList) {
        this.totalNum = totalNum;
        this.armingPlanList = armingPlanList;
    }

    public ArmingPlanVO() {
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public List<ArmingPlanList> getArmingPlanList() {
        return armingPlanList;
    }

    public void setArmingPlanList(List<ArmingPlanList> armingPlanList) {
        this.armingPlanList = armingPlanList;
    }

    @Override
    public String toString() {
        return "ArmingPlanVO{" +
                "totalNum=" + totalNum +
                ", armingPlanList=" + armingPlanList +
                '}';
    }

    public static class ArmingPlanList {

        private Integer id;
        private String armingPlanName;
        private String description;
        private String createUser;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
        private Date createTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
        private Date updateTime;
        private Integer armingPlanStatus;

        public ArmingPlanList(Integer id, String armingPlanName, String description, String createUser, Date createTime, Date updateTime, Integer armingPlanStatus) {
            this.id = id;
            this.armingPlanName = armingPlanName;
            this.description = description;
            this.createUser = createUser;
            this.createTime = createTime;
            this.updateTime = updateTime;
            this.armingPlanStatus = armingPlanStatus;
        }

        public ArmingPlanList() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getArmingPlanName() {
            return armingPlanName;
        }

        public void setArmingPlanName(String armingPlanName) {
            this.armingPlanName = armingPlanName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        public Integer getArmingPlanStatus() {
            return armingPlanStatus;
        }

        public void setArmingPlanStatus(Integer armingPlanStatus) {
            this.armingPlanStatus = armingPlanStatus;
        }

        @Override
        public String toString() {
            return "ArmingPlanList{" +
                    "id=" + id +
                    ", armingPlanName='" + armingPlanName + '\'' +
                    ", description='" + description + '\'' +
                    ", createUser='" + createUser + '\'' +
                    ", createTime=" + createTime +
                    ", updateTime=" + updateTime +
                    ", armingPlanStatus=" + armingPlanStatus +
                    '}';
        }
    }

}
