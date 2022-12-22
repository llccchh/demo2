package com.aries.jc.lch.modules.utils;

import com.aries.jc.common.boot.jwt.holder.HikJwtHolder;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.hik.crypt.Authentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Optional;

/**
 * @Author lch
 * @description
 * @Date 2022-09-22 11:40:26
 */
public class CommonUtil {
    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(CommonUtil.class);

    public static HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Optional<String> jwt = HikJwtHolder.getJwt();
        if (jwt.isPresent()) {
            headers.add("Authorization", jwt.get());
        }
        String token = null;
        try {
            token = new String(Base64.getEncoder().encode(Authentication.identifyApplyEx(null)), "UTF-8");
            headers.add("Token", token);
        } catch (Exception e) {

        }
        String userId = SessionUtil.getUserId();
        try {
            userId = URLEncoder.encode(userId, "utf-8");
        }  catch (Exception e) {

        }
        headers.add("userId", "admin");
        headers.add("domainId", "0");
        if (StringUtils.isNotBlank(userId)) {
            headers.add("userId", userId);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request != null) {
            headers.set("Cookie", request.getHeader("Cookie"));
        }
        return headers;
    }

    public static HttpHeaders createUploadHeader() {
        HttpHeaders headers = createHeader();
        MediaType type = MediaType.parseMediaType("multipart/form-data;charset=UTF-8");
        headers.setContentType(type);
        return headers;

    }
}
