package com.hikvision.ga.xpluginmgr.modules.groupc.utils;


import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.EncryptDataVO;
import com.hikvision.hik.crypt.Authentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author liuhuan53
 * @desc
 * @date 2021/12/27 16:45
 * @since 1.0
 */
public class CommonUtil {
    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(CommonUtil.class);

    public static HttpHeaders createHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("domainId", "0");
        headers.set("userId", "admin");
        String token;
        try {
            token = new String(Base64.getEncoder().encode(Authentication.identifyApplyEx(null)), "UTF-8");
            headers.add("Token", token);
            LOGGER.warn(token);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return headers;
    }

    public static <T> HttpHeaders createHeaderBySidAndDk(EncryptDataVO<T> encryptDataVO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("serviceIndex" , "aaaaa");
        String token;
        headers.add("SecuSID", encryptDataVO.getsId());
        headers.add("SecuDK", encryptDataVO.getDk());
        try {
            token = new String(Base64.getEncoder().encode(Authentication.identifyApplyEx(encryptDataVO.getsId() + encryptDataVO.getDk())), StandardCharsets.UTF_8);
            headers.add("Token", token);
            LOGGER.warn(token);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return headers;
    }


    public static HttpHeaders createHeaderWithoutUserId() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String token = null;
        try {

            headers.add("Token", new String(Base64.getEncoder().encode(Authentication.identifyApplyEx(null)), "UTF-8"));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
