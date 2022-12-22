package com.hikvision.ga.xpluginmgr.modules.groupc.operate;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import com.hikvision.ga.xpluginmgr.modules.groupc.common.RabbitMqListener;
import com.hikvision.ga.xpluginmgr.modules.groupc.constant.MyMapperStruct;
import com.hikvision.ga.xpluginmgr.modules.groupc.operate.impl.Operate;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionParamDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.LinkRecordDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkRecordService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liuyinghao5
 * @date 2022/11/7 9:35
 */

@Component
public class OperateFactory {

    @Resource
    LinkRecordService linkRecordService;
    private static Interner<String> lock = Interners.newWeakInterner();

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(OperateFactory.class);

    /**
     *
     * @param linkActions 操作参数
     * @param recordId 记录id
     *
     */
    public static void operate(List<LinkActionVO> linkActions, Integer recordId) {

        List<Operate> operates = OperateChain.getInstance().getOperateChain(linkActions);
        // 转化
        List<ActionParamDTO> actionParamDTOList = linkActions.stream().map(a -> {

            ActionParamDTO actionParamDTO =  MyMapperStruct.INSTANCE.change(a);
            actionParamDTO.setRecordId(recordId);
            return actionParamDTO;
        }).collect(Collectors.toList());

        LOGGER.info("对%s加锁", actionParamDTOList.get(0).getIndexCode());

        // 在操作时对该设备加锁，防止被其他监控事件操作干扰
        synchronized (lock.intern(actionParamDTOList.get(0).getIndexCode())){
            for (int i = 0; i < operates.size(); i++) {
                operates.get(i).doId(actionParamDTOList.get(i));
            }
        }

    }

    @Async(value = "taskExecutor")
    public void threadDoOperate(Map.Entry<Integer, List<LinkActionVO>> entry, Date time) {

        LOGGER.warn("开始执行任务" + entry.getKey());
        LinkRecordDTO linkRecordDTO = new LinkRecordDTO(entry.getKey(), time);
        Integer recordId = linkRecordService.insert(linkRecordDTO);
        LOGGER.info(linkRecordDTO.toString());
        // 执行操作
        OperateFactory.operate(entry.getValue(), recordId);
        LOGGER.warn("执行任务" + entry.getKey() + "结束");

    }


}
