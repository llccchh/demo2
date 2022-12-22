package com.aries.jc.lch.modules.enums;

import java.util.regex.Pattern;

/**
 * 维护请求路径和接口名映射，全局日志打印
 */
public enum URIEnums {
    URI_0000("null", "未定义URI枚举"),
    URI_0001("/module/test/testPost", "测试POST接口"),
    URI_0002("/module/file", "文件上传&下载接口");

    private String uri;

    private String desc;

    URIEnums(String uri, String desc) {
        this.uri = uri;
        this.desc = desc;
    }

    public String getUri() {
        return uri;
    }

    public String getDesc() {
        return desc;
    }

    public static URIEnums getEnumsByUri(String uri) {
        //正则匹配
        String regex = "[A-Za-z0-9_/]";

        for (URIEnums ue : values()) {
            if (ue.uri.equals(uri)) {
                return ue;
            }

            if (Pattern.compile(ue.uri + regex).matcher(uri).find()) {
                return ue;
            }
        }
        return URI_0000;
    }
}
