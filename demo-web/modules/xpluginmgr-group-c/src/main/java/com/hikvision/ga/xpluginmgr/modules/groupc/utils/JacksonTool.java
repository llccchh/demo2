package com.hikvision.ga.xpluginmgr.modules.groupc.utils;

import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JacksonTool {
    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(JacksonTool.class);

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String getKeyFromMap(String json, String key) {
        if (StringUtils.isBlank(json) || StringUtils.isBlank(key)) {
            return null;
        }
        Map<String,Object> map = json2Map(json);
        if(null == map || map.isEmpty() || null == map.get(key)) {
            return null;
        }
        return map.get(key).toString();
    }

    /**
     * java bean 转换为json string
     * @author wenjiahui 2018-05-03
     * @param obj
     * @return java.lang.String
     * @since 1.0.0
     */
    public static String json2Str(Object obj) {
        if (null == obj){
            return null;
        } else if(obj instanceof String){
            return obj.toString();
        } else {
            try {
                return mapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                LOGGER.info("json cast to string failed", e);
                return null;
            }
        }
    }

    /**
     * json string 转换为java bean（简单类 类数组）
     * @author wenjiahui 2018-05-21
     * @param obj
     * @param clazz
     * @return T
     * @since 1.0.0
     */
    public static <T> T json2Bean(Object obj, Class<T> clazz) {

        String json;

        if (null == obj){
            return null;
        } else if(obj instanceof String) {
            json = obj.toString();
        } else {
            json = json2Str(obj);
        }

        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            LOGGER.info("json cast to class failed, class name：" + clazz.getName(), e);
            return null;
        }
    }

    public static Map<String,Object> json2Map(Object obj) {

        String json;

        if (null == obj){
            return null;
        } else if(obj instanceof String) {
            json = obj.toString();
        } else {
            json = json2Str(obj);
        }

        if (StringUtils.isBlank(json)) {
            return null;
        }

        JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, Object.class);
        try{
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            LOGGER.info("json string cast hashMap error", e);
            return null;
        }

    }

    public static List<String> json2StrList(Object obj) {

        String json;

        if (null == obj){
            return null;
        } else if(obj instanceof String) {
            json = obj.toString();
        } else {
            json = json2Str(obj);
        }

        if(StringUtils.isBlank(json)) {
            return null;
        }

        return new ArrayList<String>(){{
            add(json);
        }};

    }

    public static List<Object> json2List(Object obj) {

        String json;

        if (null == obj){
            return null;
        } else if(obj instanceof String) {
            json = obj.toString();
        } else {
            json = json2Str(obj);
        }

        if(StringUtils.isBlank(json)) {
            return null;
        }

        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Object.class);

        try{
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            LOGGER.info("json str cast to list error", e);
            return null;
        }

    }

    public static <T> List<T> json2List(Object obj, Class<T> clazz) {

        String json;

        if (null == obj){
            return null;
        } else if(obj instanceof String) {
            json = obj.toString();
        } else {
            json = json2Str(obj);
        }

        if(StringUtils.isBlank(json)) {
            return null;
        }

        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try{
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            LOGGER.info("json string cast to list error: " + clazz.getName(), e);
            return null;
        }
    }

    public static JsonNode json2Node(String obj) {
        try {
            return mapper.readTree(obj);
        } catch (IOException e) {
            LOGGER.info("json string cast to jsonNode error", e);
            return null;
        }
    }
}
