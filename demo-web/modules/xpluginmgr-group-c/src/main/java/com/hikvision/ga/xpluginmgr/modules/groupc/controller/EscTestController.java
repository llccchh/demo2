package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.exception.DhMessageExpireException;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.DHClientDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.EscEventRouterDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.EncryptDataVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.EscService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.UniviceService;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.DhUtil;
import com.hikvision.hik.crypt.CryptErrorException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author liuyinghao5
 * @date 2022/11/16 10:01
 */

@Controller
@RequestMapping("esc")
public class EscTestController {

    @Resource
    EscService escService;

    @Resource
    UniviceService univiceService;


    @RequestMapping("aaa")
    @ResponseBody
    public BaseResult aaa() {
        univiceService.univiceKeyExchange();
        return BaseResult.success(null);
    }

    @RequestMapping("find")
    @ResponseBody
    public BaseResult find() {
        escService.findEvent();
        return BaseResult.success(null);
    }

    @RequestMapping(value = "esc/subscibeTo/event", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult subscibeTo(@RequestBody EscEventRouterDTO escEventRouterDTO) throws CryptErrorException {
       return escService.subscibeTo(escEventRouterDTO);
    }



    @RequestMapping(value = "esc/cancel/subscibeTo/event", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult cancelSubscibeTo(@RequestBody EscEventRouterDTO escEventRouterDTO) throws CryptErrorException {
        return escService.cancelSubscibeTo(escEventRouterDTO);
    }
}
