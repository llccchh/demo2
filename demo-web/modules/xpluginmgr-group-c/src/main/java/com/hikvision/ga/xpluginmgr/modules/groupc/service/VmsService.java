package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.TurnToPreIndexDTO;

/**
 * @author liuyinghao5
 * @date 2022/11/7 16:00
 */

public interface VmsService {

    /**
     * 调用vms组件拍照
     * @param indexCode 通道编号
     * @return 返回图片url
     */
    @Deprecated
    String takePicture(String indexCode);

    /**
     * @author lichanghao6
     * 转到预置点
     *
     * @param takePictureDTO 转到预置点
     * @return 操作是否成功
     */
    Boolean turnToIndex(TurnToPreIndexDTO takePictureDTO);



    Boolean setIndex(Object takePictureDTO);

}
