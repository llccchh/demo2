package com.hikvision.ga.xpluginmgr.modules.groupc.service;


import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.EncryptDataVO;

/**
 * @author liuyinghao5
 */
public interface BicService<T> {


    /**
     * 寻址并附带时间戳Token调用 POST接口
     * @param componentId  组件id
     * @param serviceId  服务id
     * @param param  请求的参数
     * @param suffix  请求的链接后缀
     * @return 返回请求结果
     */
    BaseResult<T> fetchComponentResult(String componentId, String serviceId, String suffix, Object param);

    /**
     * 寻址并附带时间戳Token调用restful GET类型接口
     * @param componentId  组件id
     * @param serviceId     服务id
     * @param url     请求链接
     * @return   返回请求结果
     */
    BaseResult<T> getComponentResult(String componentId, String serviceId, String url);



    /**
     * 带有密钥传输的接口寻址并附带时间戳Token调用restful GET类型接口
     * 寻址并附带时间戳Token调用restful GET类型接口
     * @param componentId  组件id
     * @param serviceId     服务id
     * @param url     请求链接
     * @return   返回请求结果
     */
    BaseResult<T> getComponentResultAndDh(String componentId, String serviceId, String url, EncryptDataVO<T> encryptDataVO);


    BaseResult<T> PostComponentResultAndDh(String componentId, String serviceId, String url, Object param, EncryptDataVO<T> encryptDataVO);

}
