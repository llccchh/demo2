package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.FindChannelByDeviceDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.ChannelMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.DeviceMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.DeviceService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.NmsService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.XresService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hikvision.ga.xpluginmgr.modules.groupc.constant.StringConstant.XRES_XRES_SEARCH_CHANNEL_GET_RESOURCE_FIELDS;
import static com.hikvision.ga.xpluginmgr.modules.groupc.constant.StringConstant.XRES_XRES_SEARCH_DEVICE_GET_NAME_AND_CODE;

/**
 * @author liuyinghao5
 * @date 2022/11/9 14:35
 */

// todo 将分页的页码给分离为函数，并且加一个查找设备数量的函数

@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private XresService xresService;

    @Resource
    private NmsService nmsService;

    @Override
    @SuppressWarnings("unchecked")
    public BaseResult<List<ChannelMessageVO>> getChannelMessage(FindChannelByDeviceDTO findChannelByDeviceDTO) {

        findChannelByDeviceDTO.setFields(XRES_XRES_SEARCH_CHANNEL_GET_RESOURCE_FIELDS.split("@"));
        List<JSONObject> result = (List<JSONObject>) xresService.getResourceByIndexCode(findChannelByDeviceDTO).getData().get("list");

        // 查询设备在线状态 由于xres查询结果不准确，所以查询此处
        List<ChannelMessageVO> list = result.stream().map(ChannelMessageVO::map2RegionResourceVO).collect(Collectors.toList());
        Map<Object, Object> onlineStateMap = nmsService.getDeviceOnlineState(list.stream().map(ChannelMessageVO::getChannelCode).collect(Collectors.toList()));
        list.forEach(a -> a.setStatus(onlineStateMap));

        return BaseResult.success(list);
    }

    /**
     * @param list 设备编码list
     * @return 返回通道详情列表
     */
    @Override
    public List<ChannelMessageVO> getChannelByDeviceIndexCode(List<String> list) {
        FindChannelByDeviceDTO findChannelByDeviceDTO = new FindChannelByDeviceDTO();
        findChannelByDeviceDTO.setPageNo(1);
        findChannelByDeviceDTO.setPageSize(list.size());
        findChannelByDeviceDTO.setResourceType("CAMERA");
        findChannelByDeviceDTO.setFields(XRES_XRES_SEARCH_CHANNEL_GET_RESOURCE_FIELDS.split("@"));
        Map<String, String[]> map = new HashMap<>(1);
        map.put("deviceIndexCode", list.toArray(new String[0]));
        findChannelByDeviceDTO.setExactCondition(map);
        return getChannelMessage(findChannelByDeviceDTO).getData();
    }

    /**
     * 根据通道编码返回通道详情
     *
     * @param list 通道编码list
     * @return 通道详情
     */
    @Override
    @SuppressWarnings("unchecked")
    public BaseResult<List<ChannelMessageVO>> getChannelMessageNyIndexCode(List<String> list) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resourceType", "CAMERA");
        jsonObject.put("pageNo", 1);
        jsonObject.put("pageSize", list.size());
        Map<String, String[]> map = new HashMap<>(1);
        jsonObject.put("exactCondition", map.put("deviceIndexCode", list.toArray(new String[0])));
        jsonObject.put("fields", XRES_XRES_SEARCH_CHANNEL_GET_RESOURCE_FIELDS.split("@"));
        List<JSONObject> result = (List<JSONObject>) xresService.getResourceByIndexCode(jsonObject).getData().get("list");
        if (result.size() == 0) {
            return null;
        }
        return BaseResult.success(result.stream().map(ChannelMessageVO::map2RegionResourceVO).collect(Collectors.toList()));

    }

    /**
     * 根据通道编码列表返回对应的事件能力集
     *
     * @param indexCode 通道编码
     * @return 事件列表
     */
    @Override
    public String getChannelEvent(String indexCode) {
        // 创建查询规则
        FindChannelByDeviceDTO findChannelByDeviceDTO = new FindChannelByDeviceDTO();
        findChannelByDeviceDTO.setPageNo(1);
        findChannelByDeviceDTO.setPageSize(1);
        findChannelByDeviceDTO.setResourceType("CAMERA");
        findChannelByDeviceDTO.setFields("capabilitySet".split("@"));
        Map<String, String[]> map = new HashMap<>(1);
        map.put("indexCode", indexCode.split("@"));
        findChannelByDeviceDTO.setExactCondition(map);

        // 结果返回为string
        JSONArray o = (JSONArray) (xresService.getResourceByIndexCode(findChannelByDeviceDTO).getData().get("list"));
        JSONObject jsonObject = (JSONObject) o.get(0);
        return jsonObject.get("capabilitySet").toString();

    }

    /**
     * 获取所有设备信息，包括名称和编码
     *
     *
     */
    @Override
    public List<DeviceMessageVO> getDevice(String type) {
        FindChannelByDeviceDTO findChannelByDeviceDTO = new FindChannelByDeviceDTO();
        findChannelByDeviceDTO.setPageNo(1);
        findChannelByDeviceDTO.setPageSize(5000);
        findChannelByDeviceDTO.setResourceType(type);
        findChannelByDeviceDTO.setFields(XRES_XRES_SEARCH_DEVICE_GET_NAME_AND_CODE.split("@"));
        JSONArray o = (JSONArray) (xresService.getResourceByIndexCode(findChannelByDeviceDTO).getData().get("list"));
        return o.stream().map(a -> new DeviceMessageVO((JSONObject) a)).collect(Collectors.toList());
    }
}
