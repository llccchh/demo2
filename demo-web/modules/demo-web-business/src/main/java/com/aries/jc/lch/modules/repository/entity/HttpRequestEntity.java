package com.aries.jc.lch.modules.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lichanghao6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpRequestEntity {
    private String channelIndexCode;
    private Integer lastTime;
    private Integer recordType;
}
