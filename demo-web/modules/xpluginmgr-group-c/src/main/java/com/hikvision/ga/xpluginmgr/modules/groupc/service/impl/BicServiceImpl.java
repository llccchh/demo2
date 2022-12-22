package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.aries.jc.common.bic.resttemplate.BicRestTemplate;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.EncryptDataVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.BicService;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.CommonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * @author liuyinghao5
 */

@SuppressWarnings("unchecked")
@Service
public class BicServiceImpl<T> implements BicService<T> {
    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(BicServiceImpl.class);

    @Resource
    private BicRestTemplate bicRestTemplate;
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 寻址并附带时间戳Token调用 POST接口
     *
     * @param componentId 组件id
     * @param serviceId   服务id
     * @param suffix      请求的链接后缀
     * @param param       请求的参数
     * @return 返回请求结果
     */

    @Override
    public BaseResult<T> fetchComponentResult(String componentId, String serviceId, String suffix, Object param) {
        HttpHeaders headers = CommonUtil.createHeader();
        HttpEntity<Object> httpEntity = new HttpEntity<>(param, headers);
        String uri = bicRestTemplate.getServiceURI(componentId, serviceId, null, null);
        String code = "code";
        String zero = "0";
        try {

            String object = restTemplate.exchange(uri + suffix, HttpMethod.POST, httpEntity, String.class, new HashMap<>(8)).getBody();
            JSONObject jsonObject = JSONObject.parseObject(object);
            if (zero.equals(jsonObject.get(code))){
                return (BaseResult<T>) BaseResult.success(jsonObject.get("data"));
            }
        } catch (Exception e) {
            LOGGER.error("请求错误", e);
            return null;
        }
        return (BaseResult.success(null));
    }

    /**
     * 寻址并附带时间戳Token调用 GET接口
     *
     * @param componentId 组件id
     * @param serviceId   服务id
     * @param suffix      请求的链接后缀,参数拼接到url后
     * @return 返回请求结果
     */

    @Override
    public BaseResult<T> getComponentResult(String componentId, String serviceId, String suffix) {
        StringJoiner stringJoiner = new StringJoiner(",");
        HttpHeaders headers = CommonUtil.createHeader();
        HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
        String uri = bicRestTemplate.getServiceURI(componentId, serviceId, null, null);
        try {
            Object object =  restTemplate.exchange(uri + suffix, HttpMethod.GET, httpEntity, BaseResult.class, new HashMap<>(8)).getBody();
            return (BaseResult<T>) object;
        } catch (Exception e) {
            LOGGER.error("请求错误", e);
            return null;
        }
    }

    /**
     * 带有密钥传输的接口寻址并附带时间戳Token调用restful GET类型接口
     * 寻址并附带时间戳Token调用restful GET类型接口
     *
     * @param componentId 组件id
     * @param serviceId   服务id
     * @param url         请求链接
     * @return 返回请求结果
     */
    @Override
    public BaseResult<T> getComponentResultAndDh(String componentId, String serviceId, String url, EncryptDataVO<T> encryptDataVO) {
        HttpHeaders headers = CommonUtil.createHeaderBySidAndDk(encryptDataVO);

        HttpEntity<Object> httpEntity = new HttpEntity<>(null, headers);
        String uri = bicRestTemplate.getServiceURI(componentId, serviceId, null, null);
        try {
            Object o =  restTemplate.exchange(uri + url, HttpMethod.GET, httpEntity, BaseResult.class, new HashMap<>(8)).getBody();
            return (BaseResult<T>) o;
        } catch (Exception e) {
            LOGGER.error("请求错误", e);
            return null;
        }
    }

    /**
     * 带有密钥传输的接口寻址并附带时间戳Token调用restful GET类型接口
     *
     * @param componentId 组件id
     * @param serviceId   服务id
     * @param url         请求链接
     * @return 返回请求结果
     */
    @Override
    public BaseResult<T> PostComponentResultAndDh(String componentId, String serviceId, String url, Object param, EncryptDataVO<T> encryptDataVO) {
        HttpHeaders headers = CommonUtil.createHeaderBySidAndDk(encryptDataVO);
        HttpEntity<Object> httpEntity = new HttpEntity<>(param, headers);
        String uri = bicRestTemplate.getServiceURI(componentId, serviceId, null, null);
        String code = "code";
        String zero = "0";
        try {

            String object = restTemplate.exchange(uri + url, HttpMethod.POST, httpEntity, String.class, new HashMap<>(8)).getBody();
            JSONObject jsonObject = JSONObject.parseObject(object);
            if (zero.equals(jsonObject.get(code))){
                return (BaseResult<T>) BaseResult.success(jsonObject.get("data"));
            }
        } catch (Exception e) {
            LOGGER.error("请求错误", e);
            return null;
        }
        return (BaseResult.success(null));
    }
}
