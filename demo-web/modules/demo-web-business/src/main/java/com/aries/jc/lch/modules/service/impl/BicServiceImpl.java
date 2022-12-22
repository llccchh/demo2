package com.aries.jc.lch.modules.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.aries.jc.common.BaseResult;
import com.aries.jc.common.bic.resttemplate.BicRestTemplate;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.aries.jc.lch.modules.service.BicService;
import com.aries.jc.lch.modules.utils.CommonUtil;
import com.aries.jc.lch.modules.utils.JacksonTool;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class BicServiceImpl<T> implements BicService {
    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(BicServiceImpl.class);

    @Resource
    private BicRestTemplate bicRestTemplate;
    private RestTemplate restTemplate = new RestTemplate();
    /**
     * 寻址并附带时间戳Token调用RESTful接口
     * @param componentId
     * @param serviceId
     * @param suffix
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public BaseResult<T> fetchComponentResult(String componentId, String serviceId, String suffix, Object param){
        HttpHeaders headers = CommonUtil.createHeader();

//        BaseResult<T> baseResult = bicRestTemplate.executeEx(componentId, serviceId, suffix,
//                HttpMethod.POST, headers, JacksonTool.json2Str(param), new ParameterizedTypeReference<BaseResult<T>>() {
//                }, null);
//        return baseResult;

        HttpEntity httpEntity = new HttpEntity(param, headers);
        String uri = "http://10.184.49.252:8080/vms";
        BaseResult body = null;
        String a;
        try {
            a = restTemplate.exchange(uri + suffix, HttpMethod.POST, httpEntity, String.class, new HashMap<>()).getBody();
        }finally {
            if (body != null && !body.isSuccess()){
                LOGGER.errorWithErrorCode("1", "\r\n============================================\r\nsend request == {} \r\nparams == {} \r\nresponse == {}\r\n============================================ ", uri + suffix, JacksonTool.json2Str(param),JacksonTool.json2Str(body));
            }else {
                LOGGER.debug("\r\n============================================\r\nsend request == {} \r\nparams == {} \r\nresponse == {}\r\n============================================ ", uri + suffix,JacksonTool.json2Str(param),JacksonTool.json2Str(body));
            }
        }
        return (BaseResult<T>) BaseResult.success(JSONObject.parseObject(a));
    }

    @Override
    public BaseResult getComponentResult(String componentId, String serviceId, String suffix) {
        HttpHeaders headers = CommonUtil.createHeader();
//        BaseResult<T> baseResult = bicRestTemplate.executeEx(componentId, serviceId, suffix,
//                HttpMethod.GET, headers, null, new ParameterizedTypeReference<BaseResult<T>>() {
//                }, null);
//        return baseResult;
        HttpEntity httpEntity = new HttpEntity(null, headers);
        String uri = "http://10.184.49.252:8080/vms";
        BaseResult body = null;
        try {
            body = restTemplate.exchange(uri + suffix, HttpMethod.GET, httpEntity, BaseResult.class, new HashMap<>()).getBody();
        }finally {
            if (body != null && !body.isSuccess()){
                LOGGER.errorWithErrorCode("1", "\r\n============================================\r\nsend request url == {} \r\n response == {}\r\n============================================ ", uri + suffix,JacksonTool.json2Str(body));
            }else {
                LOGGER.debug("\r\n============================================\r\nsend request url == {} \r\n response == {}\r\n============================================ ", uri + suffix,JacksonTool.json2Str(body));
            }
        }
        return body;
    }

    /**
     * 获取服务地址
     * @param componentId
     * @param serviceId
     * @return
     */
    @Override
    public String getComponentUrl(String componentId, String serviceId) {
        BicRestTemplate.SvrResultDTO resultDTO = bicRestTemplate.getFromServiceDirectory(componentId, serviceId);
        if (resultDTO == null || CollectionUtils.isEmpty(resultDTO.getAddress())) {
            return null;
        }
        BicRestTemplate.Address address = resultDTO.getAddress().get(0);
        String url = address.getNetprotocol() + "://" + address.getIp() + ":" + address.getPort();
        return url;
    }

    @Override
    public BaseResult<T> uploadFile(String componentId, String serviceId, String suffix, MultiValueMap param) {
        HttpHeaders headers = CommonUtil.createUploadHeader();
//        BaseResult<T> baseResult = bicRestTemplate.executeEx(componentId, serviceId, suffix,
//                HttpMethod.POST, headers, param, new ParameterizedTypeReference<BaseResult<T>>() {
//                }, null);
//        return baseResult;

        HttpEntity httpEntity = new HttpEntity(param, headers);
        String uri = "http://10.184.49.252:8080/vms";
        String url = uri + suffix;

        Map<String, Object> map = new HashMap<>();
        MultiValueMap<String, Object> multiValueMap = (MultiValueMap<String, Object>) param;
        multiValueMap.entrySet().stream().filter(entry ->!"file".equals(entry.getKey()))
                .forEach(entry -> map.put(entry.getKey(), entry.getValue()));
        LOGGER.info("uploadFile, url={}, param={}", url, JacksonTool.json2Str(map));
        BaseResult body = restTemplate.exchange(url, HttpMethod.POST, httpEntity, BaseResult.class, new HashMap<>()).getBody();
        return body;

    }
}
