package com.hikvision.ga.xpluginmgr.modules.groupc.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author lichanghao6
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static String object2Json(Object obj) {
        if (obj == null) {
            return "";
        }
        String result = null;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            logger.error("system error", e);
        }
        return result;
    }

    public static Map<?, ?> jsonToMap(String json) {
        return json2Object(json, Map.class);
    }

    public static <T> T json2Object(String json, Class<T> cls) {
        T result = null;
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            result = objectMapper.readValue(json, cls);
        } catch (IOException e) {
            logger.error("system error", e);
        }

        return result;
    }

    public static <T> T conveterObject(Object srcObject, Class<T> destObjectType) {
        String jsonContent = object2Json(srcObject);
        return json2Object(jsonContent, destObjectType);
    }

    public static <T> List<T> fromJsonList(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

}
