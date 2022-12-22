package com.hikvision.ga.xpluginmgr.modules.groupc.common;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.modules.groupc.operate.OperateFactory;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.LinkRecordDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkActionService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkRecordService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.XresService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.naming.Name;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * @author liuyinghao5
 * @date 2022/11/8 18:10
 */

@Component
public class RabbitMqListener {
    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(RabbitMqListener.class);


    @Resource
    OperateFactory operateFactory;
    @Resource
    XresService xresService;
    @Resource
    LinkRecordService linkRecordService;
    @Resource
    LinkActionService linkActionService;




    @RabbitListener(queues = "groupc.region.listener")
    public void addNewRegion(String message) {

        LOGGER.info("区域目录更新" + message);
        xresService.getRegionIndexCode();
    }


    @Transactional(rollbackFor = {Exception.class})
    // @RabbitListener(queues = "esc_test", executor = "executor")
    public void subscibeTo(String message) {
        LOGGER.debug("事件产生" + message);
        // 处理数据
        JSONObject jsonObject = JSONObject.parseObject(message);
        // 获取起点设备
        JSONObject eventParam = jsonObject.getJSONObject("params").getJSONArray("events").getJSONObject(0);
        String indexCode = eventParam.getString("srcIndex");
        // 获取时间
        Date time = eventParam.getDate("happenTime");
        Integer eventType = eventParam.getInteger("eventType");
        List<LinkActionVO> linkActions = linkActionService.getOperateBySort(indexCode, eventType);
        if (linkActions.size() == 0) {
            return;
        }

        Map<Integer, List<LinkActionVO>> map = new HashMap<>(16);
        for (LinkActionVO action : linkActions) {
            List<LinkActionVO> mapList = map.get(action.getArmingPlanId());
            if (mapList == null) {
                List<LinkActionVO> list = new ArrayList<>();
                list.add(action);
                map.put(action.getArmingPlanId(), list);
            } else {
                mapList.add(action);
                map.put(action.getArmingPlanId(), mapList);
            }
        }
        for (Map.Entry<Integer, List<LinkActionVO>> entry : map.entrySet()) {
            operateFactory.threadDoOperate(entry, time);

        }

        LOGGER.debug("操作生成");

        Integer recordId = linkRecordService.insert(new LinkRecordDTO(linkActions.get(0).getArmingPlanId(), time));
        // 执行操作
        OperateFactory.operate(linkActions, recordId);

    }


}