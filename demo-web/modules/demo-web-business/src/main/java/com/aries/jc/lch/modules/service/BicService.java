package com.aries.jc.lch.modules.service;


import com.aries.jc.common.BaseResult;
import org.springframework.util.MultiValueMap;

/**
 * @author lichanghao6
 */
public interface BicService<T> {


    /**
     * 寻址并附带时间戳Token调用RESTful POST接口
     */
    BaseResult<T> fetchComponentResult(String componentId, String serviceId, String suffix, Object param);

    /**
     * 寻址并附带时间戳Token调用RESTful GET类型接口
     * @param componentId
     * @param serviceId
     * @param url
     * @return
     */
    BaseResult<T> getComponentResult(String componentId, String serviceId, String url);


    /**
     * 获取服务地址
     */
    String getComponentUrl(String componentId, String serviceId);


    /**
     * 文件上传
     * @param componentId
     * @param serviceId
     * @param url
     * @param param
     * @return
     */
    BaseResult<T> uploadFile(String componentId, String serviceId, String url, MultiValueMap<String, Object> param);

}
