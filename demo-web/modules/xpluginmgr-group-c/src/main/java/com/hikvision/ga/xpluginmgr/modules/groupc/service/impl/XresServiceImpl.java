package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.XresRegionMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.BicService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.XresService;
import com.hikvision.ga.xpluginmgr.tool.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hikvision.ga.xpluginmgr.modules.groupc.constant.StringConstant.XRES_XRES_SEARCH_GET_REGION_BY_PARAM;

/**
 * @author liuyinghao5
 * @date 2022/11/4 16:03
 */

@Service
public class XresServiceImpl implements XresService {

    @Resource
    BicService<Map<String, Object>> bicService;

    @Resource
    RedisUtil redisUtil;


    /**
     * post请求xres-search接口
     *
     * @param uri        请求后缀
     * @param jsonObject 请求体
     * @return 返回请求结果
     */
    private BaseResult<Map<String, Object>> xresSearchPost(String uri, Object jsonObject) {
        return bicService.fetchComponentResult("xres", "xres-search", uri, jsonObject);
    }


    /**
     * @return 返回查詢到的所有區域的詳細信息，保存到list裏面，并将结果缓存到redis中
     */

    @Override
    @SuppressWarnings("unchecked")
    public BaseResult<List<XresRegionMessageVO>> getRegionIndexCode() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("treeCode", "0");
        jsonObject.put("pageNo", 1);
        jsonObject.put("pageSize", 100);

        List<JSONObject> linkedHashMaps = (List<JSONObject>) xresSearchPost(XRES_XRES_SEARCH_GET_REGION_BY_PARAM, jsonObject).getData().get("list");
        List<XresRegionMessageVO> list = linkedHashMaps.stream().map(XresRegionMessageVO::linkedHashMap2Vo).collect(Collectors.toList());

        List<Object> redisList = new ArrayList<>();
        list.forEach(a -> redisList.add(String.join("@", a.getName(), a.getIndexCode(), a.getParentIndexCode())));


        redisUtil.del("xpm-group-c-region-tree-resource-list");
        redisUtil.lSet("xpm-group-c-region-tree-resource-list", redisList);
        return BaseResult.success(list);
    }




    @Override
    public BaseResult<Map<String, Object>> getResourceByIndexCode(Object param) {

        return xresSearchPost("/service/rs/resource/v1/findResources", param);
    }





    @Override
    public BaseResult<Map<String, Object>> getAllResourceByType(String type) {
        return null;
    }


}