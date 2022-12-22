package com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/10 11:11
 */

@TableName(value = "xp_link_time_strategy")
public class TimeStrategy {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;
    private Date createTime;
    private Date updateTime;

    public TimeStrategy(Integer id, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday, Date createTime, Date updateTime) {
        this.id = id;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TimeStrategy() {
    }

    public TimeStrategy(Map<String, String[]> map) {
        map.forEach((key, value) -> {
            if ("monday".equals(key)) {
                setMonday(String.join("@", value));
            }
            if ("tuesday".equals(key)) {
                setTuesday(String.join("@", value));
            }
            if ("wednesday".equals(key)) {
                setWednesday(String.join("@", value));
            }
            if ("thursday".equals(key)) {
                setThursday(String.join("@", value));
            }
            if ("friday".equals(key)) {
                setFriday(String.join("@", value));
            }
            if ("saturday".equals(key)) {
                setSaturday(String.join("@", value));
            }
            if ("sunday".equals(key)) {
                setSunday(String.join("@", value));
            }
        });
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
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

    @Override
    public String toString() {
        return "TimeStrategy{" +
                "id=" + id +
                ", monday='" + monday + '\'' +
                ", tuesday='" + tuesday + '\'' +
                ", wednesday='" + wednesday + '\'' +
                ", thursday='" + thursday + '\'' +
                ", friday='" + friday + '\'' +
                ", saturday='" + saturday + '\'' +
                ", sunday='" + sunday + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
