package com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo;

import com.aries.jc.common.BaseResult;
import lombok.Data;

/**
 * @author liuyinghao5
 * @date 2022/11/28 19:21
 */

@Data
public class BasePage<T> extends BaseResult<T>{
    private Long total;

    public BasePage() {
    }

    public static <T> BasePage<T> success(T data, Long total) {
        return new BasePage("0", "SUCCESS", data, total);
    }


    public BasePage(String code, String msg, T data ,Long total) {
        super(code,msg,data);
        this.total = total;
    }
}
