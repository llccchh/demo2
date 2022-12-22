package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @author liuyinghao5
 * @date 2022/11/7 11:02
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendEmileDTO {



    private String[] recipients;

    private String subject;

    private String content;

    private String[] attachments;
}
