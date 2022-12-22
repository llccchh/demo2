package com.hikvision.ga.xpluginmgr.modules.groupc.operate.impl;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionParamDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;

/**
 * @author liuyinghao5
 * @date 2022/11/7 9:31
 */

public interface Operate {

    /**
     * 操作的接口函数
     * @param vo 操作参数
     *
     */
    void doId(ActionParamDTO vo);

}
