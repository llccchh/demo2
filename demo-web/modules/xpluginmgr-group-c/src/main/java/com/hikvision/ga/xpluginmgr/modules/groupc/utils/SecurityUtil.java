package com.hikvision.ga.xpluginmgr.modules.groupc.utils;

import com.hikvision.hik.crypt.Authentication;
import com.hikvision.hik.crypt.CryptErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;


public class SecurityUtil {

    private static final Logger log = LoggerFactory.getLogger(SecurityUtil.class);

    public static final String  SUCCESS = "0";

    /**
     * <p>安全传输token验证</p>
     * @param token
     * @param sessionID
     * @return
     */
    public static boolean authenticateToken(String token, String sessionID) {
        try {
            byte[] tokenByte =hexStringToBytes(token);
            if (StringUtils.equals(Authentication.identifyCheck(tokenByte, sessionID), SUCCESS)){
                return true;
            }
        } catch (CryptErrorException e) {
            log.error("authenticateToken", e);
        }
        return false;
    }

    /**
     * <p>生成协商表示</p>
     * @return
     * @return
     */
    public static String generateSecuSID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String secuSID = str.replace("-", "");
        return secuSID;
    }

    /**
     * <p>16进制字符串转换成字节数组</p>
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase().replace(" ", "");
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * <p>字节数组转换为16进制字符串</p>
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    /**
     * <p>生成数据秘钥原文,用来加密敏感数据</p>
     * @return
     */
//    public static byte[] generateDataKey() {
//        try {
//            return A.getDataKey();
//        } catch (CryptErrorException e) {
//            log.error("generateDataKey", e);
//        }
//        return null;
//    }
//



    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}
