package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/9 14:41
 */


@Data
@AllArgsConstructor
public class FindResourceDTO {

    private Integer pageSize;
    private Integer pageNo;
    private String[] orgIndexCodes;
    private String resourceType;
    private String[] fields;
    private Map<String, String> condition;
    private Map<String, String[]> exactCondition;


    public FindResourceDTO() {

        this.pageNo = 1;
        this.pageSize = 2000;
    }

}
