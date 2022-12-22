package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.TimeStrategy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/10 13:51
 */

public class TimeStrategyDTO {

    private Map<String, String[]> dataList;

    public TimeStrategyDTO(Map<String, String[]> dataList) {
        this.dataList = dataList;
    }

    public TimeStrategyDTO() {
    }

    public Map<String, String[]> getDataList() {
        return dataList;
    }

    public void setDataList(Map<String, String[]> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "TimeStrategyDTO{" +
                "dataList=" + dataList +
                '}';
    }

    public  TimeStrategy changeToEntity(){
        return new TimeStrategy(dataList);
    }

}
