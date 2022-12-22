package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.controller.LinkGroupController;
import com.hikvision.ga.xpluginmgr.modules.groupc.mapper.LinkEventMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkEvent;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.ArmingPlanService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.DacService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.DeviceService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkEventService;
import com.hikvision.hik.crypt.CryptErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liting43
 * @date 2022/11/14 10:12
 * @description
 */
@Service
public class LinkEventServiceImpl implements LinkEventService {

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(LinkGroupController.class);

    @Resource
    private DacService dacService;

    @Autowired
    private LinkEventMapper linkEventMapper;

    @Resource
    private ArmingPlanService armingPlanService;

    @Resource
    private DeviceService deviceService;


    /**
     * @author liting43
     * 根据设备编码查询可触发事件
     *
     * @param deviceIndexCode 设备编码
     * @return BaseResult
     */
    @Override
    public BaseResult<List<LinkEvent>> getDeviceLinkEventList(String deviceIndexCode){
        // 根据设备编码查询设备能力集
        JSONObject object = (JSONObject) dacService.getAbilityByDevice(deviceIndexCode).getData();
        JSONArray capability = object.getJSONArray(deviceIndexCode);
        String[] ability =  capability.toArray(new String[0]);

        // 筛选能力集事件
        return BaseResult.success(filterEvent(ability));
    }

    /**
     * @author liting43
     * 根据通道编码查询可触发事件
     *
     * @param channelIndexCode 通道编码
     * @return BaseResult
     */
    @Override
    public BaseResult getChannelLinkEventList(String channelIndexCode){
        String cap = deviceService.getChannelEvent(channelIndexCode);
        String[] capability = cap.split("@");
        // 筛选能力集事件
        return BaseResult.success(filterEvent(capability));
    }

    /**
     * @author liting43
     * 根据事件能力集筛选可触发事件
     *
     * @param eventAbility 事件能力集
     * @return 可触发事件的信息
     */
    public List<LinkEvent> filterEvent(String[] eventAbility){
        List<LinkEvent> linkEvents = new ArrayList<>();
        for (String ea: eventAbility){
            LambdaQueryWrapper<LinkEvent> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(LinkEvent::getCapability, ea);
            List<LinkEvent> les = linkEventMapper.selectList(lambdaQueryWrapper);
            for (LinkEvent le: les){
                linkEvents.add(le);
            }
        }
        return linkEvents;
    }

    @Override
    public Integer getEventTypeCode(Integer id){
        return linkEventMapper.getEventTypeCode(id);
    }



    /**
     * 工作日的17点00分00秒执行
     *
     */
    @Scheduled(cron="30 1 18 ? * MON-FRI")
    public void startByDate(){
        List<Integer> ids = armingPlanService.getMessageByTime(1, 1);
        try {
            armingPlanService.enableArmingPlanBatch(0,  ids);
        } catch (CryptErrorException e) {
            e.printStackTrace();
        }

    }



    /**
     * 工作日的9:00执行
     *
     */
    @Scheduled(cron="0 0 9 ? * MON-FRI")
    public void endByDate(){

        try {
            armingPlanService.enableArmingPlanBatch(1,  armingPlanService.getMessageByTime(1, 0));
        } catch (CryptErrorException e) {
            e.printStackTrace();
        }

    }



}
