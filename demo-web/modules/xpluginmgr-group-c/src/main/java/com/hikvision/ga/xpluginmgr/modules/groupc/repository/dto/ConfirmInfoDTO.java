package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/16 10:49
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmInfoDTO {
    private List<Integer> ids;
    private String confirmInfo;
    private String confirmOpinion;
}
