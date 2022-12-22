package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.FindResourceDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.GetResourceByRegionDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.ChannelMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.DeviceMessageResultVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.RegionVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.NmsService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.RegionResourceService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.XresService;
import com.hikvision.ga.xpluginmgr.tool.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.hikvision.ga.xpluginmgr.modules.groupc.constant.StringConstant.XRES_XRES_SEARCH_CHANNEL_GET_RESOURCE_FIELDS;


/**
 * @author liuyinghao5
 * @date 2022/11/8 16:15
 */
@Service
public class RegionResourceServiceImpl implements RegionResourceService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private XresService xresService;

    @Resource
    private NmsService nmsService;


    @Override
    public BaseResult<List<RegionVO>> getRootIndexCode(String name) {


        List<Object> list = redisUtil.lGet("xpm-group-c-region-tree-resource-list", 0, -1);
        List<RegionVO> regionVOList = new ArrayList<>();

        list.forEach(a -> regionVOList.add(RegionVO.stringArray2Pojo(a.toString())));
        if (StringUtils.isEmpty(name)) {
            return BaseResult.success(regionVOList);
        }
        // 正则表达式 模糊查询
        String pattern = ".*?" + name + ".*";
        return BaseResult.success(regionVOList.stream().filter(a ->
                Pattern.matches(pattern, a.getRegionName())
        ).collect(Collectors.toList()));
    }

    /**
     * 通过区域查找通道信息
     *
     * @param findResourceDTO 此处为前端传来的值
     * @return 返回通道消息列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public BaseResult<List<ChannelMessageVO>> getResourceByRegion(FindResourceDTO findResourceDTO) {

        return BaseResult.success(null);
    }


    @Override
    @SuppressWarnings("unchecked")
    public BaseResult<List<DeviceMessageResultVO>> getDeviceMessageByRegion(GetResourceByRegionDTO dto) {
        return getDeviceMessageByRegion(dto.getDeviceMessage(), null, dto.getOrgIndexCode().split("@"), getRegionMessageMap(), dto.getResourceType());
    }

    /**
     * 通过区域信息获取区域内的所有设备信息
     *
     * @param indexCode  设备编码，可为空  为空查询全部
     * @param regionCode 区域编码 可为空 为空查询全部
     * @param regionMap  区域map映射，用来将查到的indexCode获取为调用名字  com.hikvision.ga.xpluginmgr.modules.groupc.service.impl.RegionResourceServiceImpl#getRegionMessageMap()函数
     * @param type       设备下面通道的资源类型，为空则为camera
     */
    @Override
    @SuppressWarnings("unchecked")
    public BaseResult<List<DeviceMessageResultVO>> getDeviceMessageByRegion(String name,  List<String> indexCode, String[] regionCode, Map<String, String> regionMap, String type) {
        if (type == null){
            type = "camera";
        }
        type = type.toLowerCase();
        // 设备信息填充
        FindResourceDTO findResourceDTO = new FindResourceDTO();


        Map<String, String[]> condition = new HashMap<>(4);

        if (indexCode != null && indexCode.size() != 0) {
            condition.put("indexCode", indexCode.toArray(new String[0]));
            findResourceDTO.setExactCondition(condition);
        }
        if (regionCode != null && regionCode.length != 0) {
            findResourceDTO.setOrgIndexCodes(regionCode);
        }
        findResourceDTO.setResourceType("ENCODE_DEVICE");


        BaseResult<Map<String, Object>> object = xresService.getResourceByIndexCode(findResourceDTO);
        List<JSONObject> map = (List<JSONObject>) object.getData().get("list");
        List<DeviceMessageResultVO> list = map.parallelStream().map(DeviceMessageResultVO::map2DeviceResourceVO).collect(Collectors.toList());
        // 获取编码设备集合
        List<String> deviceIndexCode = list.parallelStream().map(DeviceMessageResultVO::getDeviceCode).collect(Collectors.toList());
        list.forEach(a -> a.setRegion(regionMap.get(a.getRegion())));

        // 添加设备的在线状态
        Map<Object, Object> onlineStateMap = nmsService.getAllResourceOnlineState("encodeDevice", list.parallelStream().map(DeviceMessageResultVO::getDeviceCode).collect(Collectors.toList()));
        list.forEach(a -> a.setStatus(onlineStateMap));

        // 根据name筛选设备
        if (!StringUtils.isEmpty(name)){
            list = list.stream().filter(a -> findByReName(name ,a.getDeviceName()) || findByReName(name, a.getDeviceCode()) ).collect(Collectors.toList());
        }


        // 获取通道信息
        List<DeviceMessageResultVO.ChannelMessage> channelMessage;
        if ("".equals(type)){
            channelMessage =  getChannelMessageByType(deviceIndexCode);

        }
        else {
            channelMessage= getChannelMessage(deviceIndexCode,
                    null, null, type == null ? "CAMERA" : type, regionMap);

        }

        // 通道和设备绑定
        tieChannelAndDevice(list, channelMessage, regionMap);

        return BaseResult.success(list);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<DeviceMessageResultVO.ChannelMessage> getChannelMessage(List<String> deviceIndexCode, List<String> indexCode, String[] regionCode, String type, Map<String, String> regionMap) {

        Map<String, String[]> condition = new HashMap<>(16);
        if (deviceIndexCode != null && deviceIndexCode.size() != 0) {
            condition.put("deviceIndexCode", deviceIndexCode.toArray(new String[0]));
        }
        if (indexCode != null && indexCode.size() != 0) {
            condition.put("indexCode", indexCode.toArray(new String[0]));
        }


        // 搜索条件创建
        FindResourceDTO findResourceDTO = new FindResourceDTO();
        findResourceDTO.setExactCondition(condition);
        findResourceDTO.setFields(XRES_XRES_SEARCH_CHANNEL_GET_RESOURCE_FIELDS.split("@"));
        findResourceDTO.setResourceType(type);
        if (regionCode != null) {
            findResourceDTO.setOrgIndexCodes(regionCode);
        }

        findResourceDTO.setPageSize(300);
        findResourceDTO.setPageNo(1);


        BaseResult<Map<String, Object>> object = xresService.getResourceByIndexCode(findResourceDTO);
        List<JSONObject> map = (List<JSONObject>) object.getData().get("list");
        List<DeviceMessageResultVO.ChannelMessage> list = map.parallelStream().map(a -> {
            DeviceMessageResultVO.ChannelMessage channelMessage = new DeviceMessageResultVO.ChannelMessage();
            channelMessage.map2RegionResourceVO(a, regionMap);
            return channelMessage;
        }).collect(Collectors.toList());


        // 可检测在线的type
        String usedType = "CAMERA";
        if (usedType.equals(type)) {
            Map<Object, Object> onlineStateMap = nmsService.getAllResourceOnlineState(type.toLowerCase(), list.parallelStream().map(DeviceMessageResultVO.ChannelMessage::getChannelCode).collect(Collectors.toList()));
            list.forEach(a -> a.setStatus(onlineStateMap));
        }


        return list;
    }

    @Override
    public List<DeviceMessageResultVO.ChannelMessage> getChannelMessageByType(List<String> deviceIndexCode) {

            List<DeviceMessageResultVO.ChannelMessage> channelMessage = new ArrayList<>();
            channelMessage.addAll( getChannelMessage(deviceIndexCode,
                    null, null, "CAMERA", getRegionMessageMap()));
                channelMessage.addAll( getChannelMessage(deviceIndexCode,
                    null, null, "IO", getRegionMessageMap()));
                        channelMessage.addAll( getChannelMessage(deviceIndexCode,
                    null, null, "TALK", getRegionMessageMap()));
            return channelMessage;


    }

    /**
     * 根据设备列表和通道列表绑定关系
     */
    private void tieChannelAndDevice(List<DeviceMessageResultVO> deviceList, List<DeviceMessageResultVO.ChannelMessage> channelMessageList, Map<String, String> regionMap) {

        Map<String, String> deviceMap = new HashMap<>(8);
        deviceList.forEach(a -> deviceMap.put(a.getDeviceCode(), a.getDeviceName()));
        // 设备编码和通道信息列表对应map
        Map<String, List<DeviceMessageResultVO.ChannelMessage>> map = new HashMap<>(16);
        // 保存设备通道映射关系，并把编码转为名字
        channelMessageList.forEach(a -> a.changeToMap(map, deviceMap));
        // 将通道装到他们所属的设备中
        for (DeviceMessageResultVO devices : deviceList) {
            devices.setChannelInfo(map.get(devices.getDeviceCode()));
        }


    }

    /**
     * 区域编码名字映射字典
     *
     * @return 返回区域map key 区域编码 value 区域名字
     */


    @Override
    public Map<String, String> getRegionMessageMap() {
        List<RegionVO> region = getRootIndexCode(null).getData();

        Map<String, String> regionMap = new HashMap<>(8);

        region.forEach(a -> regionMap.put(a.getRegionIndexCode(), a.getRegionName()));
        return regionMap;
    }


    /**
     * 模糊搜索匹配
     * @param name  要搜索的资源名称 如"P"
     * @param res   资源 如PTZ球机
     * @return   返回true 或者false
     */
    private boolean findByReName(String name, String res){
        String pattern = ".*?" + name + ".*?";

                return Pattern.matches(pattern, res);

    }


}
