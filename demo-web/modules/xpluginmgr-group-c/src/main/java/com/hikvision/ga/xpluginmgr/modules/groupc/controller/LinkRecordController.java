package com.hikvision.ga.xpluginmgr.modules.groupc.controller;

import com.aries.jc.common.BaseResult;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ConfirmInfoDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.FindRecordsListByParamDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.LinkRecordDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.StatisticalDataByDateDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.BasePage;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.MyLinkRecordListVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.RecordLevByDateVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkRecordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/14 14:57
 */

@RestController
@RequestMapping("/cameraLink/v1/record")
public class LinkRecordController {

    @Resource
    LinkRecordService linkRecordService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult<Integer> insert(@RequestBody LinkRecordDTO linkRecordDTO){

        return BaseResult.success(linkRecordService.insert(linkRecordDTO));
    }


    @RequestMapping(value = "/getRecordList.do",method = RequestMethod.POST)
    @ResponseBody
    public BasePage<List<MyLinkRecordListVO>> getRecordList(@RequestBody FindRecordsListByParamDTO findRecordsListByParamDTO,
                                                            @RequestParam("pageSize") Integer pageSize, @RequestParam("pageNo") Integer pageNo){
        return (linkRecordService.getRecordListByPage(findRecordsListByParamDTO,pageSize, pageNo));

    }

    /**
     * @author liting43
     * 获取联动记录详情
     */
    @GetMapping("/getRecordInfo.do")
    public BaseResult getRecordInfo(@Param("recordId") Integer recordId){
        return linkRecordService.getRecordInfo(recordId);
    }

    /**
     * @author liting43
     * 批量确认联动记录报告信息
     */
    @PostMapping("/confirmInfo.do")
    public BaseResult confirmInfo(@RequestBody ConfirmInfoDTO confirmInfo){
        return linkRecordService.confirmInfo(confirmInfo);
    }


    /**
     * @author liting43
     * 获取查询下拉框数据
     */
    @GetMapping("/selectData.do")
    public BaseResult getSelectData(){
        return linkRecordService.getSelectData();
    }
}
