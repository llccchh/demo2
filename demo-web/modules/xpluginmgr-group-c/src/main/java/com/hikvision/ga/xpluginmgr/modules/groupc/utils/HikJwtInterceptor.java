package com.hikvision.ga.xpluginmgr.modules.groupc.utils;

import com.aries.jc.common.boot.jwt.holder.HikJwtHolder;
import com.aries.jc.common.boot.jwt.reference.service.JwtService;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author liuhuan53
 * @desc
 * @date 2021/12/14 19:16
 * @since 1.0
 */
public class HikJwtInterceptor extends HandlerInterceptorAdapter {
    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(HikJwtInterceptor.class);

    /**
     * 缓存认证信息
     */
    private static final Cache<String,String> JWT_CACHE = CacheBuilder.newBuilder().maximumSize(10).expireAfterWrite(10, TimeUnit.MINUTES).build();
    /**
     * 将ctgt转换为认证信息放入header
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization)) {
            return true;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userInfo = authentication == null?null:authentication.getName();
        if (StringUtils.isBlank(userInfo)) {
            return true;
        }
        String[] principals = userInfo.split("&&");
        String userId = principals[0];
        SessionUtil.setUserId(userId);
        if (principals.length > 1) {
            String jwt = JWT_CACHE.getIfPresent(userId);
            if (StringUtils.isNotBlank(jwt)){
                HikJwtHolder.setJwt(jwt);
                return true;
            }
        }
        if (principals.length > 2){
            //通过ctge生成jwt
            String ctgt = principals[principals.length - 2];
            LOGGER.debug("ctgt == {}",ctgt);
            //生成jwt
            JwtService jwtService = SpringBeanUtil.getBean(JwtService.class);
            String jwt = jwtService.applyCT(ctgt);
            JWT_CACHE.put(userId,jwt);
            //jwt放入threadLocal
            HikJwtHolder.setJwt(jwt);
        }
        return true;
    }
}
