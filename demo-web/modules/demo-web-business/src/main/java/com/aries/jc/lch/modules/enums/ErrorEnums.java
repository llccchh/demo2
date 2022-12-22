package com.aries.jc.lch.modules.enums;

public enum ErrorEnums {

    M_0000("0000", "成功"),
    M_7777("7777", "请求参数有误"),
    M_2000("2000", "文件不存在"),
    M_2001("2001", "文件上传失败"),
    M_9999("9999", "系统繁忙, 请稍后重试");

    private String errCode;
    private String errMsg;

    ErrorEnums(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public static ErrorEnums getEnumByCode(String code) {
        for (ErrorEnums bt : values()) {
            if (bt.errCode.equals(code)) {
                return bt;
            }
        }
        return null;
    }

    public String getErrCode() {
        return errCode;
    }


    public String getErrMsg() {
        return errMsg;
    }

}
