package com.hikvision.ga.xpluginmgr.modules.groupc.exception;

import com.aries.jc.common.exception.BusinessDataException;
import com.hikvision.ga.xpluginmgr.modules.groupc.constant.XpmCameraLinkErrorCode;

/**
 * @author liting43
 * @date 2022/11/4 16:57
 * @description 枪球联动错误处理
 */
public class XpmCameraLinkException extends BusinessDataException {

    public XpmCameraLinkException(XpmCameraLinkErrorCode errorCode){
        super(errorCode.getCode(), errorCode.getMsg(), null);
    }

    public XpmCameraLinkException(XpmCameraLinkErrorCode errorCode, Throwable cause){
        super(errorCode.getCode(), errorCode.getMsg(), cause);
    }

    public XpmCameraLinkException(String code, String msg, Object data) {
        super(code, msg, data);
    }

    public XpmCameraLinkException(String code, String msg, Object data, Throwable cause) {
        super(code, msg, data, cause);
    }
}
