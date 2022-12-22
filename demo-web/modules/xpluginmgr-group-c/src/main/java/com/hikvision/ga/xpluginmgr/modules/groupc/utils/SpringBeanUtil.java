package com.hikvision.ga.xpluginmgr.modules.groupc.utils;

import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Lazy(false)
public class SpringBeanUtil implements ApplicationContextAware, DisposableBean {

    private static final AriesJcLogger log = AriesJcLoggerFactory.getLogger(SpringBeanUtil.class);

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        Optional<T> bean;
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext not inject");
        }
        try {
            return (T) applicationContext.getBean(name);
        } catch (BeansException e) {
            log.error("Fail to get bean {} from application context by name.", name, e);
        }
        return null;
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> clazz) {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext not inject");
        }
        try {
            return applicationContext.getBean(clazz);
        } catch (BeansException e) {
            log.errorWithErrorCode("000", "Fail to get bean {} from application context by clazz.", clazz.getName(), e);
        }
        return null;
    }


    @Override
    public void destroy() {
        applicationContext = null;
    }
}
