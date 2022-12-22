package com.aries.jc.lch.modules.exception;


import com.aries.jc.lch.modules.enums.ErrorEnums;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 自定义异常类
 */
public class BusinessRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1961845140311676051L;

    private String errCode;
    private String errMsg;


    public BusinessRuntimeException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BusinessRuntimeException(ErrorEnums errorEnums) {
        this.errCode = errorEnums.getErrCode();
        this.errMsg = errorEnums.getErrMsg();
    }

    @Override
    public String getMessage() {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        objectNode.put("retCode", errCode);
        objectNode.put("retMsg", errMsg);
        return objectNode.toString();
    }
}
