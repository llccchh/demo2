package com.aries.jc.lch.modules.service;

import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.repository.dto.EndVideoDTO;
import com.aries.jc.lch.modules.repository.dto.StartVideoDTO;

/**
 * @author lichanghao6
 */
public interface CameraService {

    BaseResult<Object> takePicture(String cameraIndexCode);
    BaseResult<Object> startVideo(StartVideoDTO startVideoDTO);
    BaseResult<Object> getMessage(EndVideoDTO endVideoDTO);
    BaseResult<Object> endVideo(EndVideoDTO endVideoDTO);

}
