package com.hikvision.ga.xpluginmgr.modules.groupc.operate;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.modules.groupc.operate.impl.Operate;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionParamDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.TurnToPreIndexDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.VmsService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.impl.VmsServiceImpl;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.SpringApplicationUtils;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;

/**
 * @author liuyinghao5
 * @date 2022/11/7 10:10
 */

public class TurnToPreIndex implements Operate {


    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(TurnToPreIndex.class);
    VmsService vmsService;

    private volatile static TurnToPreIndex turnToPreIndex;


    private TurnToPreIndex() {
        vmsService = SpringApplicationUtils.getBean(VmsServiceImpl.class);
    }


    public static TurnToPreIndex getInstance() {
        if (turnToPreIndex == null) {
            synchronized (TurnToPreIndex.class) {
                if (turnToPreIndex == null) {
                    turnToPreIndex = new TurnToPreIndex();
                }
            }
        }
        return turnToPreIndex;
    }


    @Override
    public void doId(ActionParamDTO linkActionVO) {
        synchronized (TurnToPreIndex.class){
            LOGGER.info("开始轉到預置點");
            long start = System.currentTimeMillis();
            TurnToPreIndexDTO turnToPreIndexDTO = new TurnToPreIndexDTO(linkActionVO);
            takePictureInPresetPoint(turnToPreIndexDTO);
            LOGGER.info("轉到預置點耗時" + (System.currentTimeMillis() - start));
        }

    }

    /**
     * @param turnToPreIndexDTO 转到预置点参数
     * @return 返回要插入数据库的dto列表
     * @author lichanghao6
     * 转到预置点
     */

    public Boolean takePictureInPresetPoint(TurnToPreIndexDTO turnToPreIndexDTO) {

        Boolean result = vmsService.turnToIndex(turnToPreIndexDTO);
        return result;
    }
}
