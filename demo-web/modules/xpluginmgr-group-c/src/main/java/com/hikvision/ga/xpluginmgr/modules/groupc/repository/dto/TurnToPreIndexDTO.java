package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lichanghao6
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnToPreIndexDTO {

    private String cameraIndexCode;
    /**
     * 开始或停止操作，0-开始 1-停止
     */
    private Integer action;
    /**
     * 控制命令,GOTO_PRESET为转到预置点
     */
    private String command;
    /**
     * 转动速度
     */
    private Integer speed;
    /**
     * 预置点编号
     */
    private Integer presetIndex;

    public TurnToPreIndexDTO(ActionParamDTO linkActionVO){
        this.cameraIndexCode = linkActionVO.getIndexCode();
        this.action = 1;
        this.command = "GOTO_PRESET";
        this.speed = 100;
        this.presetIndex = JSONObject.parseObject(linkActionVO.getJsonObject()).getInteger("preIndexCode");
    }

}
