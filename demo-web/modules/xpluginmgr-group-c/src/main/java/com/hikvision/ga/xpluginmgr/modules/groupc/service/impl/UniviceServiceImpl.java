package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.hikvision.ga.xpluginmgr.modules.groupc.service.UniviceService;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.DhUtil;
import com.hikvision.hik.crypt.CryptErrorException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuyinghao5
 * @date 2022/11/16 11:30
 */

@Service
public class UniviceServiceImpl implements UniviceService {


    @Resource
    DhUtil dhUtil;

    @Override
    public void univiceKeyExchange() {

        try {
            dhUtil.launchNegotiation("univice", "univice", "/application/applicationdata/v1/security/keyExchange");
        } catch (CryptErrorException e) {
            e.printStackTrace();
        }


    }
}
