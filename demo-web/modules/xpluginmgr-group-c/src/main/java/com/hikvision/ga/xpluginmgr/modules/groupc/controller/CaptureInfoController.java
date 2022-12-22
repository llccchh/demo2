package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.CaptureInfoStorePictureDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.CaptureInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liuyinghao5
 * @date 2022/11/14 14:02
 */

@Controller
@RequestMapping("captureInfo")
public class CaptureInfoController {


    @Resource
    CaptureInfoService captureInfoService;

    @ResponseBody
    @RequestMapping(value = "pictureStore.test", method = RequestMethod.POST)
    public BaseResult<Integer> insertPictureDate(@RequestBody CaptureInfoStorePictureDTO captureInfoStorePictureDTO){

        return BaseResult.success(captureInfoService.insert(captureInfoStorePictureDTO));
    }


    @ResponseBody
    @RequestMapping(value = "download.test", method = RequestMethod.POST)
    public BaseResult<Integer> download(@RequestBody CaptureInfoStorePictureDTO captureInfoStorePictureDTO, HttpServletResponse response){

        return BaseResult.success(captureInfoService.insert(captureInfoStorePictureDTO));
    }

}
