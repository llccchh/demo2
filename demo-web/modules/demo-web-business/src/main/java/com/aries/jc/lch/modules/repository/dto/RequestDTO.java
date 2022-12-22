package com.aries.jc.lch.modules.repository.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @program: i-security
 * @author: gaoliang
 * @create 2019-10-25 13:51
 * @desc: 全局请求处理
 **/
@Data
public class RequestDTO implements Serializable {

    private static final long serialVersionUID = -3713033623861465131L;

    //请求系统
    @NotBlank(message = "systemId不能为空")
    private String systemId;

    //请求时间
    private Date timestamp = new Date();

    //请求数据
    private Map<String, Object> data;


}
