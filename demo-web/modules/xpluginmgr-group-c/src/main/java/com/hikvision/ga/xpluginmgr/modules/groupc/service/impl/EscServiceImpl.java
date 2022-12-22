package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.exception.DhMessageExpireException;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.EscEventRouterDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.EncryptDataVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.BicService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.EscService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.UniviceService;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.DhUtil;
import com.hikvision.hik.crypt.CryptErrorException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyinghao5
 * @date 2022/11/15 21:27
 */

@Service
public class EscServiceImpl implements EscService {

    @Resource
    BicService bicService;

    @Resource
    DhUtil dhUtil;

    @Resource
    UniviceService univiceService;

    private <T> BaseResult<Object> EscPost(String uri, Object param, EncryptDataVO<T> encryptDataVO) {
        return bicService.PostComponentResultAndDh("univice", "univice", uri, param, encryptDataVO);
    }

    private BaseResult<Object> EscGet(String uri) {
        return bicService.getComponentResult("univice", "univice", uri);
    }


    @Override
    public BaseResult subscibeTo(EscEventRouterDTO escEventRouterDTO) throws CryptErrorException {


        return EscPost("/application/event/v1/eventRouter", escEventRouterDTO, cancelAndSubscibeToCommon(escEventRouterDTO));

    }

    @Override
    public BaseResult cancelSubscibeTo(EscEventRouterDTO escEventRouterDTO) throws CryptErrorException {

        return EscPost("/application/event/v1/eventRouterDelete", escEventRouterDTO, cancelAndSubscibeToCommon(escEventRouterDTO));
    }


    private EncryptDataVO<List<String>> cancelAndSubscibeToCommon(EscEventRouterDTO escEventRouterDTO) throws CryptErrorException {
        EncryptDataVO<List<String>> encryptDataVO;

        List<String> list = Arrays.stream(escEventRouterDTO.getEventRouters()).map(EscEventRouterDTO.EventRouters::getEventDest).collect(Collectors.toList());
        try {
            encryptDataVO = dhUtil.EncryptData("univice", list);
        } catch (DhMessageExpireException | CryptErrorException ce) {
            univiceService.univiceKeyExchange();
            encryptDataVO = dhUtil.EncryptData("univice", list);
        } finally {
            // todo 加个日志
        }
        EscEventRouterDTO.EventRouters[] routers = escEventRouterDTO.getEventRouters();
        for (int i = 0; i < routers.length; i++) {
            routers[i].setEventDest(encryptDataVO.getT().get(i));
        }
        return encryptDataVO;
    }


    @Override
    public BaseResult<Object> findEvent() {
        return EscGet("/application/event/v1/eventRouter?serviceIndex=");
    }





}
