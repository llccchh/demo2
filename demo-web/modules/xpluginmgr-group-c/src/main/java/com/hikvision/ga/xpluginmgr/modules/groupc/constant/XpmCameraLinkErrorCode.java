package com.hikvision.ga.xpluginmgr.modules.groupc.constant;

import com.aries.jc.common.log.ErrorCode;

/**
 * @author liting43
 * @date 2022/11/4 16:46
 * @description 枪球联动相关错误码
 */
public enum XpmCameraLinkErrorCode implements ErrorCode {

    /**
     * 错误码规则：64位（二进制）的无符号整形数字，分为标识码（44位），类别码（4位），失败或异常码（16位）；定义和输出采用16进制，以0x开头，全小写字符
     * xplugimgr对应的规则： 标识码：0xe4b（运管分配），类别码（0：表示组件自定义  4:表示常规错误码，我们默认为0），失败或异常码（我们不区分失败和异常，16位按照模块自定义）
     * 模块划分： 高4位区分模块：0：系统通用  1：协议管理模块  2：协议转换模块  3：任务管理模块 4：系统配置模块 5：采集系统信息管理模块
     */

    /**
     * 系统通用错误码
     */
    ERR_SYSTEM_ERROR("0xe4b00001", "系统错误"),
    ERR_PARAMETER_NOT_AVAILABLE("0xe4b00002", "参数不符合要求。"),
    ERR_UNCORRECT_AUTH_FILE("0xe4b00003","上传文件格式不正确"),
    ERR_UPLOAD_TEMPLATE_FILE("0xe4b00004","上传文件失败"),
    ERR_PSPC_HTTP_REQUEST_ERROR("0xe4b00005","部标http请求失败"),
    ERR_VIIDEXT_HTTP_REQUEST_ERROR("0xe4b00006","1400k扩展http请求失败"),
    ERR_CROP_HTTP_REQUEST_ERROR("0xe4b00007","海康数据对接标准规范http请求失败"),
    ERR_ALREADY_TASK_RELATED("0xe4b00008","已经配置的任务关联此信息，不能删除或者修改"),
    ERR_XPLUGGINMGR_PG_ERROR("0xe4b00009","连接xpluginmgr数据库错误"),
    ERR_XPLUGINMGR_CLOUD_STORE_ERROR("0xe4b00010","云存储接口返回异常"),

    /**
     * 设备相关错误：ERR_DEVICE_XX_XX，0xe4b14001开始
     */
    ERR_DEVICE_GET_FAIL("0xe4b14001", "获取设备失败"),
    ERR_CHANNEL_ADD_FAIL("0xe4b14002", "存储联动分组通道信息失败"),
    ERR_CHANNEL_DEL_FAIL("0xe4b14003", "删除联动分组通道信息失败"),

    /**
     * 联动分组相关错误：ERR_LINK_GROUP_XX_XX，0xe4b15001开始
     */
    ERR_LINK_GROUP_GET_FAIL("0xe4b15001", "获取联动分组信息失败"),
    ERR_LINK_GROUP_NOT_EXIST("0xe4b15002", "数据不存在"),
    ERR_LINK_GROUP_ADD_FAIL("0xe4b15003", "添加联动分组信息失败"),
    ERR_LINK_GROUP_EDIT_FAIL("0xe4b15004", "修改联动分组信息失败"),
    ERR_LINK_GROUP_DEL_FAIL("0xe4b15005", "删除联动分组信息失败"),
    ERR_LINK_GROUP_INFO_FAIL("0xe4b15006", "获取联动分组详情失败"),

    /**
     * 联动规则相关错误：ERR_ARMING_PLAN_XX_XX，0xe4b16001开始
     */
    ERR_ARMING_PLAN_NOT_EXIST("0xe4b16001", "联动规则数据不存在"),
    ERR_ARMING_PLAN_SAVE_FAIL("0xe4b16002","添加或更新规则失败"),
    ERR_ARMING_PLAN_DEL_FAIL("0xe4b16003", "删除联动规则失败"),
    ERR_ARMING_PLAN_RUN_FAIL("0xe4b16004", "联动规则启用失败");

    private String code;
    private String msg;
    //组件标识码
    private static final String errorCodePrefix = "0xe4b";
    //组件自定义错误码
    private static final String categoryCodeOfComponent = "0";
    //常规错误码
    private static final String categoryCodeOfCommon = "4";

    private XpmCameraLinkErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
