package com.aries.jc.lch.modules.repository.dto;

import com.aries.jc.lch.modules.enums.ErrorEnums;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @program: i-security
 * @author: gaoliang
 * @create 2019-10-25 13:51
 * @desc: 全局返回处理
 **/
@Data
public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = -2227385967802023043L;

    private String retCode;

    private String retMsg;

    //返回时间
    private Date timestamp = new Date();

    private Map<String, Object> data;

    public static ResponseDTO convertSuccessResponseDTO(Map<String, Object> data){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setRetCode(ErrorEnums.M_0000.getErrCode());
        responseDTO.setRetMsg(ErrorEnums.M_0000.getErrMsg());
        responseDTO.setData(data);
        return responseDTO;
    }

}
