package com.hikvision.ga.xpluginmgr.modules.groupc.constant;

/**
 * @author liuyinghao5
 * @date 2022/11/4 16:37
 */

public class StringConstant {

    /**
     * dac-dms url 请求
     */

    public static final String DAC_DMS_SEARCH_ABILITY_BY_DEVICE_CODE_URL = "/dms/v1/deviceAbility";

    /**
     * xres-xres-search url 请求 根据区域参数和统计参数查询区域下面有资源的区域
     */
    public static final String XRES_XRES_SEARCH_GET_REGION_BY_PARAM = "/service/rs/v2/region/findRegionsByParam";


    /**
     *xres 查询返回字段条件常量
     */
    public static final String XRES_XRES_SEARCH_CHANNEL_GET_RESOURCE_FIELDS = "resourceOrg@cascadeCode@latitude@name@indexCode@recordLocation@cameraType@longitude@deviceIndexCode@capabilitySet@intelligentSet@status@resourceType";
    public static final String XRES_XRES_SEARCH_DEVICE_GET_NAME_AND_CODE = "indexCode@name";




}
