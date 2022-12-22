package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.TurnToPreIndexDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.VmsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuyinghao5
 * @date 2022/11/14 15:26
 */

@RestController
@RequestMapping("vms")
public class VmsTestController {

    @Resource
    private VmsService vmsService;

    @RequestMapping("/takePicture")
    @ResponseBody
    public void get(){
        vmsService.takePicture("b818099009674183a72842ee5da83994");
    }

    @RequestMapping("/turnToIndex")
    public Boolean turnToIndex(@RequestBody TurnToPreIndexDTO takePictureDTO) {
        return vmsService.turnToIndex(takePictureDTO);
    }


    @RequestMapping("/set")
    public Boolean set(@RequestBody TurnToPreIndexDTO takePictureDTO) {
        return vmsService.setIndex(takePictureDTO);
    }


}
