package com.hikvision.ga.xpluginmgr.modules.groupc.utils;

import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.tool.utils.PropertiesUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author liuyinghao5
 * @date 2022/11/28 11:16
 */

public class MyPropertiesUtil {

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(MyPropertiesUtil.class);
    private static final String OPERATE_FILE_NAME = "operate.properties";

    public static volatile Properties OPERATE_PROPERTIES;

    static {
        OPERATE_PROPERTIES = readProperties();
    }

//    public static Map<String, Object> getOperateMap(){
//
//
//
//
//
//    }

    private static Properties readProperties() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(OPERATE_FILE_NAME);

        Properties config = new Properties();
        try {
            assert inputStream != null;
            config.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return config;
    }

}
