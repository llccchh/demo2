package com.hikvision.ga.xpluginmgr.modules.groupc.operate;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.hikvision.ga.xpluginmgr.modules.groupc.exception.TakePictureException;
import com.hikvision.ga.xpluginmgr.modules.groupc.operate.impl.Operate;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionParamDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.CaptureInfoStorePictureDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.CaptureInfo;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.CaptureInfoService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.VmsService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.impl.CaptureInfoServiceImpl;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.impl.VmsServiceImpl;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.CommonUtil;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.SpringApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author liuyinghao5
 * @date 2022/11/7 10:10
 */

public class TakePhotoOperate implements Operate {

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(TakePhotoOperate.class);
    private volatile static TakePhotoOperate takePhotoOperate;

    private CaptureInfoService service;

    private TakePhotoOperate() {
        service = SpringApplicationUtils.getBean(CaptureInfoServiceImpl.class);

    }


    public static TakePhotoOperate getInstance() {
        if (takePhotoOperate == null) {
            synchronized (TakePhotoOperate.class) {
                if (takePhotoOperate == null) {
                    takePhotoOperate = new TakePhotoOperate();
                }
            }
        }
        return takePhotoOperate;
    }

    @Override
    public void doId(ActionParamDTO linkActionVO) {
        JSONObject jsonObject = JSONObject.parseObject(linkActionVO.getJsonObject());
        long start = System.currentTimeMillis();
        LOGGER.info("开始拍照");
        String indexCode = linkActionVO.getIndexCode();

        takePictureDelayTime(indexCode, jsonObject.getLong("captureTime") , jsonObject.getLong("captureInterval"),jsonObject.getInteger("captureTimes"), linkActionVO.getRecordId());
        LOGGER.info("拍照耗时" + (System.currentTimeMillis() - start));
    }

    /**
     * 间隔抓图
     *
     * @param indexCode 对应通道编码
     * @param ms        间隔时间 单位毫秒
     * @param count     拍照次数
     * @return 返回要插入数据库的dto列表
     */

    public Boolean takePictureDelayTime(String indexCode, Long ms,  Long dms, Integer count,  Integer recordId) {
        List<CaptureInfo> list = new ArrayList<>();


        try {
            Thread.sleep(ms * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }


        for (int i = 0; i < count; i++) {
            if (i !=0){
                try {
                    long start = System.currentTimeMillis();

                    Thread.sleep(dms * 1000);
                    LOGGER.info("等待时间" + (System.currentTimeMillis() - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }

            CaptureInfo captureInfo = new CaptureInfo(recordId, indexCode, new Date(), postTakePicture(indexCode));
            list.add(captureInfo);
        }
        return  service.insertList(list);

    }

    private String postTakePicture(String indexCode) {
        HttpHeaders headers = CommonUtil.createHeader();
        JSONObject js = new JSONObject();
        js.put("cameraIndexCode", indexCode);

        HttpEntity<Object> httpEntity = new HttpEntity<>(js, headers);
        String uri = "http://10.184.49.252:8257/vms/api/v3/manualCapture";
        String code = "code";
        String zero = "0";
        RestTemplate restTemplate = new RestTemplate();
        String object = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class, new HashMap<>(8)).getBody();
        JSONObject jsonObject = JSONObject.parseObject(object);
        if (zero.equals(jsonObject.get(code))) {
            return jsonObject.getJSONObject("data").getString("picUrl");
        }

        throw new TakePictureException("拍照异常" + jsonObject.getString("msg"));

    }
}
