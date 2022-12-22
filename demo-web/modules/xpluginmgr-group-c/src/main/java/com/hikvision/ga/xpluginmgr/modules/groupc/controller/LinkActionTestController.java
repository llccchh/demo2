package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkActionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyinghao5
 * @date 2022/11/21 10:38
 */
@RequestMapping("action")
@RestController
public class LinkActionTestController {

    @Resource
    private LinkActionService linkActionService;

    @RequestMapping("aaa")
    public List<LinkActionVO> get(){

        List<LinkActionVO> a =  linkActionService.getOperateBySort("f2a31557c2794a14a356411e3b0ed75a", 131588);
        return a;
    }
}
