package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liuyinghao5
 * @date 2022/11/18 10:09
 */

public class StatisticalDataByDateDTO {

    private Date now;
    private Date pass;


    public StatisticalDataByDateDTO(Date now, Date pass) {
        this.now = now;
        this.pass = pass;
    }

    public StatisticalDataByDateDTO() {
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public Date getPass() {
        return pass;
    }

    public void setPass(Date pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "now=" + now +
                ", pass=" + pass +
                '}';
    }


    public static StatisticalDataByDateDTO getMyData(String state) {

        StatisticalDataByDateDTO statisticalDataByDateDTO = new StatisticalDataByDateDTO();
        Calendar calendar = Calendar.getInstance();

        //现在日期
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);


        statisticalDataByDateDTO.setNow(calendar.getTime());
        if ("year".equals(state)) {
            // 过去七个月
            calendar.setTime(statisticalDataByDateDTO.getNow());
            calendar.add(Calendar.MONTH, -7);
            statisticalDataByDateDTO.setPass(calendar.getTime());
        } else {

            // 过去七天
            calendar.setTime(statisticalDataByDateDTO.getNow());
            calendar.add(Calendar.DATE, -7);
            statisticalDataByDateDTO.setPass(calendar.getTime());
        }

        return statisticalDataByDateDTO;
    }

    
//    public List<Map<String, List<Map<String, String>>>> getMapDateByDto(String state){
//        List<Map<String, List<Map<String, String>>>> list = new ArrayList<>(7);
//
//        if ("year".equals(state)){
//
//
//        }
//
//        return
//    }
}
