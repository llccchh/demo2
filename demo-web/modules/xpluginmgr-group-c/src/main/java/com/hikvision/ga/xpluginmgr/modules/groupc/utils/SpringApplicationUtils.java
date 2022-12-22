package com.hikvision.ga.xpluginmgr.modules.groupc.utils;

import com.hikvision.security.patch.io.apache.BooleanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

import java.util.Objects;

/**
 * @author liuyinghao5
 * @date 2022/11/22 21:48
 */

@SuppressWarnings("unused")
@Slf4j
@Component
public final class SpringApplicationUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringApplicationUtils.applicationContext == null) {
            SpringApplicationUtils.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     */
    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name Bean名称
     * @return Bean
     */
    public static Object getBean(String name) {
        if (BooleanUtils.isTrue(validationApplication())) {
            return null;
        }
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz bean类型
     * @param <T>   类型
     * @return Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        if (BooleanUtils.isTrue(validationApplication())) {
            return null;
        }
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  bean名字
     * @param clazz bean类型
     * @param <T>   分享
     * @return 获取的bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        if (BooleanUtils.isTrue(validationApplication())) {
            return null;
        }
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 用于读取配置文件
     *
     * @param key   配置文件的key
     * @param clazz 读取出来的配置属性的类型
     * @param <T>   类型
     * @return 读取出来的配置属性
     */
    public static <T> T getPropertiesValue(String key, Class<T> clazz) {
        Environment environment = getBean(Environment.class);
        if (Objects.isNull(environment)) {
            log.info("没有获取到Environment 对象");
            return null;
        }
        return environment.getProperty(key, clazz);
    }

    /**
     * 用于读取配置文件 读取出来的默认都是String类型
     *
     * @param key 配置文件的key
     * @return value
     */
    public static String getPropertiesValue(String key) {
        return getPropertiesValue(key, String.class);
    }

    /**
     * 验证Application
     */
    private static Boolean validationApplication() {
        if (applicationContext == null) {
            log.info("applicationContext为空");
            return true;
        }
        return false;
    }
}

