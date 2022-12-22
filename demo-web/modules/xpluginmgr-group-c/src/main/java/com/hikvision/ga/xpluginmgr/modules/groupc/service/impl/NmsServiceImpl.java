package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.DeviceIfOnlineDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.BicService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.NmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/11 14:53
 */
@Service
public class NmsServiceImpl implements NmsService {

    @Resource
    BicService<JSONObject> bicService;


    private BaseResult<JSONObject> nmsService(String uri, Object param) {
        return bicService.fetchComponentResult("nms", "nmsweb", uri, param);

    }


    /**
     * 查询设备是否在线
     *
     * @return 返回设备在线状态
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<Object, Object> getDeviceOnlineState(List<String> indexCode) {

        List<JSONObject> jsonArray = (List<JSONObject>) nmsService("/api/v1/monitor/status/page", new DeviceIfOnlineDTO(indexCode)).getData().get("list");
        return changeData2HashMap(jsonArray);
    }


    /**
     * 查询所有设备是否在线
     *
     * @return 返回设备在线状态
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<Object, Object> getAllResourceOnlineState(String type, List<String> codes) {
        JSONObject jsonObject = JSON.parseObject("{\n" +
                "    \"pageSize\": 100,\n" +
                "    \"pageNo\": 1,\n" +
                "}");

        jsonObject.put("resourceType", type);
        if (codes.size() != 0){
            jsonObject.put("indexCodes", codes.toArray());
        }


        List<JSONObject> jsonArray = (List<JSONObject>) nmsService("/api/v1/monitor/status/page", jsonObject).getData().get("list");
        return changeData2HashMap(jsonArray);
    }


    /**
     * 查询所有设备是否在线
     *
     * @return 返回设备在线状态
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<Object, Object> getAllDeviceOnlineState() {
        JSONObject jsonObject = JSON.parseObject("{\n" +
                "    \"pageSize\": 100,\n" +
                "    \"pageNo\": 1,\n" +
                "    \"resourceType\": \"encodeDevice\"\n" +
                "}");


        List<JSONObject> jsonArray = (List<JSONObject>) nmsService("/api/v1/monitor/status/page", jsonObject).getData().get("list");
        return changeData2HashMap(jsonArray);
    }


    /**
     * 转换函数， JSON转map
     * */
    private Map<Object, Object> changeData2HashMap(List<JSONObject> jsonObjects) {
        Map<Object, Object> map = new HashMap<>(8);
        jsonObjects.forEach(a -> map.put(a.get("indexCode"), a.get("online")));
        return map;

    }

}
