package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.hikvision.ga.xpluginmgr.tool.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.util.Objects;

/**
 * @author liuyinghao5
 * @date 2022/11/18 10:55
 */

public class RecordLevByDateVO {

    private String lev;
    private String date;

    public RecordLevByDateVO(String lev, String date) {
        this.lev = lev;
        this.date = date;
    }

    public RecordLevByDateVO() {
    }

    public String getLev() {
        return lev;
    }

    public void setLev(String lev) {
        this.lev = lev;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return
                "lev=" + lev +
                        ",date=" + date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecordLevByDateVO that = (RecordLevByDateVO) o;
        return StringUtils.equals(lev, that.lev) && StringUtils.equals(date, that.date);

    }

    @Override
    public int hashCode() {
        return Objects.hash(lev, date);
    }
}
