package com.aries.jc.lch.modules.config;

import com.aries.jc.lch.modules.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lichanghao6
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         *  调用我们创建的SessionInterceptor。
         *  addPathPatterns("/api/**)的意思是这个链接下的都要进入到SessionInterceptor里面去执行
         *  excludePathPatterns("/login")的意思是login的url可以不用进入到SessionInterceptor中，直接放过执行。
         *  registry.addInterceptor(sessionInterceptor).excludePathPatterns("/login");
         *  registry.addInterceptor(sessionInterceptor).excludePathPatterns("/verify");
         *  注意：如果像上述这样写是不可以的。这样等于是创建了多个Interceptor。而不是只有一个Interceptor
         */
        // registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**");
        // registry.addInterceptor(loginHandlerInterceptor).excludePathPatterns("/**");
    }
}
