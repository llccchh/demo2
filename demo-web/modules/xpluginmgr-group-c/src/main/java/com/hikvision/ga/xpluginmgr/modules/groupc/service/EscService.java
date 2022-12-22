package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.EscEventRouterDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.EncryptDataVO;
import com.hikvision.hik.crypt.CryptErrorException;
import org.springframework.stereotype.Service;

/**
 * @author liuyinghao5
 * @date 2022/11/15 21:26
 */


public interface EscService {

    BaseResult<String> subscibeTo(EscEventRouterDTO escEventRouterDTO) throws CryptErrorException;


    BaseResult<String> cancelSubscibeTo(EscEventRouterDTO escEventRouterDTO) throws CryptErrorException;

    BaseResult<Object> findEvent();


}
