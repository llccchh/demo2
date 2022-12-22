package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.SendEmileDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.BicService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.MailpsService;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.CommonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author liuyinghao5
 * @date 2022/11/7 10:58
 */
@Service
public class MailpsServiceImpl implements MailpsService {


    private RestTemplate restTemplate = new RestTemplate();




    @SuppressWarnings("unchecked")
    private BaseResult<Object> mailpsPost(String uri, SendEmileDTO sendEmileDTO) {
        HttpEntity httpEntity = new HttpEntity(sendEmileDTO, CommonUtil.createHeader());

        BaseResult ba = restTemplate.exchange("http://10.184.49.252:8063/mailps" + uri, HttpMethod.POST, httpEntity, BaseResult.class, new HashMap<>(8)).getBody();
        return ba;
    }


    @Override
    public BaseResult<Object> sendEmile(SendEmileDTO sendEmileDTO) {

        return mailpsPost("/mailService/v1/mail", sendEmileDTO);

    }
}
