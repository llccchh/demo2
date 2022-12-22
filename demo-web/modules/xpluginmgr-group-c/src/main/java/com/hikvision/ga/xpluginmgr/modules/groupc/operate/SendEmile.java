package com.hikvision.ga.xpluginmgr.modules.groupc.operate;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.modules.groupc.operate.impl.Operate;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionParamDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.SendEmileDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.MailpsService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.impl.MailpsServiceImpl;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.SpringApplicationUtils;

/**
 * @author liuyinghao5
 * @date 2022/11/7 10:11
 */

public class SendEmile implements Operate {

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(SendEmile.class);
    private volatile static SendEmile sendEmile;

    private MailpsService mailpsService;


    private SendEmile() {
        mailpsService = SpringApplicationUtils.getBean(MailpsServiceImpl.class);

    }


    public static SendEmile getInstance() {
        if (sendEmile == null) {
            synchronized (SendEmile.class) {
                if (sendEmile == null) {
                    sendEmile = new SendEmile();
                }
            }
        }
        return sendEmile;
    }




    @Override
    public void doId(ActionParamDTO jsonObject) {
        LOGGER.info("开始發郵件");
        long start = System.currentTimeMillis();
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.getJsonObject());
        SendEmileDTO sendEmileDTO = SendEmileDTO.builder().recipients((jsonObject1.getString("addressee") + "@hikvision.com.cn").split(",")).content(jsonObject1.getString("mailMsg")).subject("Link Event Happened").build();
        mailpsService.sendEmile(sendEmileDTO);
        LOGGER.info("發郵件耗時" + (System.currentTimeMillis() - start));
    }
}
