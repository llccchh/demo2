package com.hikvision.ga.xpluginmgr.modules.groupc.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.bic.resttemplate.BicRestTemplate;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.modules.groupc.exception.DataEncryptDataException;
import com.hikvision.ga.xpluginmgr.modules.groupc.exception.DhMessageExpireException;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.DHClientDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.EncryptDataVO;
import com.hikvision.ga.xpluginmgr.tool.utils.RedisUtil;
import com.hikvision.hik.crypt.Authentication;
import com.hikvision.hik.crypt.CloaderDHI;
import com.hikvision.hik.crypt.CryptErrorException;
import com.hikvision.hik.crypt.DHCryptUtils;
import com.hikvision.hik.crypt.util.Base64Utils;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liuyinghao5
 * @date 2022/11/14 20:42
 */
@Component
public class DhUtil {


    static {
        try {
            //注册核心服务加密信息，不能修改
            CloaderDHI.loaderInitlize("Rsh@18969188291!", "hikvision@(rsh)", 50000, 16, "PBKDF2WithHmacSHA256");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String encName = "AES128/ECB/PKCS5Padding";


    @Resource
    RedisUtil redisUtil;

    @Resource
    private BicRestTemplate bicRestTemplate;
    private RestTemplate restTemplate = new RestTemplate();

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(DhUtil.class);


    public void launchNegotiation(String componentId, String serviceId, String myUri) throws CryptErrorException {


        // 甲方公钥 ,私钥
        byte[] publicKey1, privateKey1;
        // 甲方生成的P值
        String p, g;
        // 生成DH算法中的prime值
        String prime = DHCryptUtils.randomPrime(1024);
        // 生成甲方密钥对
        Map<String, Object> keyMapC = DHCryptUtils.initKey(prime, null);
        p = DHCryptUtils.getDHP(keyMapC).toString(16);
        g = DHCryptUtils.getDHG(keyMapC).toString(16);
        publicKey1 = DHCryptUtils.getPublicKey(keyMapC);
        privateKey1 = DHCryptUtils.getPrivateKey(keyMapC);

        // 组装body体
        DHClientDTO client = new DHClientDTO();
        client.setP(p);
        client.setG(g);
        client.setCurveType("0");
        client.setPublicKey(SecurityUtil.bytesToHexString(publicKey1));
        client.setSecuSIDA(UUID.randomUUID().toString().replace("-", ""));
        client.setSupports(encName);

        // 生成token
        String tokenSecurity = Base64Utils.encodeBase64String(Authentication.identifyApply(client.getSecuSIDA() + SecurityUtil.bytesToHexString(publicKey1) + p + g), false);
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(mediaType);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.set("Token", tokenSecurity);

        HttpEntity<Object> entity = new HttpEntity(JSON.toJSONString(client), headers);
        String re = null;
        String uri = bicRestTemplate.getServiceURI(componentId, serviceId, null, null);
        String SecuSID = null;
        LOGGER.error("entity>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", Objects.requireNonNull(entity.getBody()).toString());
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri + myUri, HttpMethod.POST, entity, String.class, new HashMap<>());
            HttpHeaders a = responseEntity.getHeaders();
            SecuSID = a.getFirst("SecuSID");
            re = responseEntity.getBody();
        } finally {
            if (re != null) {
                LOGGER.errorWithErrorCode("1", "\r\n============================================\r\nsend request == {} \r\nparams == {} \r\nresponse == {}\r\n============================================ ");
            } else {
                LOGGER.error("\r\n============================================\r\nsend request == {} \r\nparams == {} \r\nresponse == {}\r\n============================================ ", JSONObject.toJSONString(entity));
            }
        }

        String a = JSONObject.parseObject(re).getJSONObject("data").getString("PublicKey");
        Integer expireTime = JSONObject.parseObject(re).getJSONObject("data").getInteger("expireTime");

        // 本地密钥存储到redis
        Map<String, Object> key1 = DHCryptUtils.getSecretKey(p, g, SecurityUtil.hexStringToBytes(a), privateKey1, encName);
        SaveMapMessage(key1, serviceId);
        redisUtil.set("xpm_" + serviceId + "_SecuSID", SecuSID, expireTime / 1000);


    }


    private void SaveMapMessage(Map<String, Object> map, String serverId) {
        try {
            FileOutputStream fos =
                    new FileOutputStream(serverId + "keyMap.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }



    /**
     *加密数据
     */
    @SuppressWarnings("unchecked")
    public <T> EncryptDataVO<T> EncryptData(String serverId, T data) throws CryptErrorException {
        String sId = (String) redisUtil.get("xpm_" + serverId + "_SecuSID");
        if (sId == null){
            throw new DhMessageExpireException("密钥交换过期，请重新交换密钥");
        }
        Map<String, Object> mapKey = getMyKeyMap(serverId);
        byte[] localIv = DHCryptUtils.getLocalIv(mapKey);
        byte[] localKey = DHCryptUtils.getLocalKey(mapKey);
        byte[] dataKey = SecurityUtil.hexStringToBytes(SecurityUtil.generateSecuSID());
        // 获取dk
        String dk = SecurityUtil.bytesToHexString(DHCryptUtils.encrypt(dataKey, localKey, localIv, encName));

        if (data == null){
            return new EncryptDataVO<>(dk, sId);
        }

        // 加密数据
        if (data instanceof String) {
            return EncryptDataVO.getData(dk, (T) Base64Utils.encodeBase64String(DHCryptUtils.encrypt(((String) data).getBytes(StandardCharsets.UTF_8), dataKey, localIv, encName)), sId);
        }

        if (data instanceof List) {
            List<Object> list = ((List<String>) data).stream().map(a -> a.getBytes(StandardCharsets.UTF_8)).map(a -> {
                try {
                    return Base64Utils.encodeBase64String(DHCryptUtils.encrypt(a, dataKey, localIv, encName));
                } catch (CryptErrorException e) {
                    e.printStackTrace();
                    throw new DataEncryptDataException("数据加密失败，请查看数据是否合规");
                }
            }).collect(Collectors.toList());
            return EncryptDataVO.getData(dk, (T)list, sId);
        }

        throw new DataEncryptDataException("数据类型不合规，仅支持list和string");
    }


    @SuppressWarnings("unchecked")
    public Map<String, Object> getMyKeyMap(String serverId) {
        Map<String, Object> map;
        try {
            FileInputStream fis = new FileInputStream(serverId + "keyMap.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (Map<String, Object>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return null;
        }
        return map;
    }

}
