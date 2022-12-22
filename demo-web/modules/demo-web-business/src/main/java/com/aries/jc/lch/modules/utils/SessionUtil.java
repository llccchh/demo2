package com.aries.jc.lch.modules.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author liuhuan53
 * @desc
 * @date 2021/12/27 14:58
 * @since 1.0
 */
public class SessionUtil {

    private static final String USER_ID = "USER_ID";

    private static HttpSession getSession(){
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest().getSession();
    }

    public static String getSessionId(){
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest().getSession().getId();
    }
    /**
     * 设置用户账号名称
     */
    public static void setUserId(String userId){
        getSession().setAttribute(USER_ID,userId);
    }

    public static String getUserId(){
		return (String)getSession().getAttribute(USER_ID);
    }
}
