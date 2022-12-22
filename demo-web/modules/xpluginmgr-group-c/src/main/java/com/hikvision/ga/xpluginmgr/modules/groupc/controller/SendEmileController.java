package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.SendEmileDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.MailpsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author liuyinghao5
 * @date 2022/11/7 11:05
 */
@Controller
@RequestMapping("emile")
public class SendEmileController {

    @Resource
    private MailpsService mailpsService;

    @RequestMapping("a")
    @ResponseBody
    public  void main() {
        SendEmileDTO sendEmileDTO = new SendEmileDTO();
        String[] address = new String[1];
        address[0] = "liting43@hikvision.com.cn";
        sendEmileDTO.setRecipients(address);
        sendEmileDTO.setSubject("test");
        sendEmileDTO.setContent("test");
        mailpsService.sendEmile(sendEmileDTO);
    }

}
