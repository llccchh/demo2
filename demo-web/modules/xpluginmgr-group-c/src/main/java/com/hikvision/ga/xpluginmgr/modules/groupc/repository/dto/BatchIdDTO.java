package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/8 10:59
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchIdDTO {
    private List<Integer> ids;

}
