package com.aries.jc.lch.modules.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Slf4j
public class JacksonUtil {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 设置时间格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 自动去除为null的字段
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        // 忽略json字符串和实体不对应的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static ObjectMapper getObjectMapper() {
        return JacksonUtil.objectMapper;
    }

    /**
     * object转json
     *
     * @param object 对象
     * @return 返回
     */
    public static String objectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        return null;
    }

    /**
     * @param obj
     * @return 实体转MAP
     */
    public static Map bean2Map(Object obj) {
        try {
            return objectMapper.readValue(objectToJson(obj), Map.class);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        return null;
    }

    /**
     * @param
     * @return MAP转实体
     */
    public static Object map2Bean(Map map, Class clazz) {
        try {
            return objectMapper.readValue(objectToJson(map), clazz);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        return null;
    }


    /**
     * json 反序列化成 简单对象
     *
     * @param json      json字符串
     * @param valueType 对象
     * @return 返回
     */
    public static <T> T jsonToObject(String json, Class<T> valueType) {
        try {
            if (null != json && !"".equals(json)) {
                return objectMapper.readValue(json, valueType);
            }
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        return null;
    }


}
