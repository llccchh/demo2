package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.aries.jc.common.BaseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ConfirmInfoDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.FindRecordsListByParamDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.LinkRecordDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.StatisticalDataByDateDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkRecord;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.BasePage;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.MyLinkRecordListVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.RecordLevByDateVO;

import java.util.List;
import java.util.Map;

/**
 * @author liuyinghao5
 * @date 2022/11/14 14:58
 */

public interface LinkRecordService extends IService<LinkRecord> {

    /**
     * 保存联动记录
     *
     * @param record record
     * @return record_id
     */
    Integer insert(LinkRecordDTO record);

    BaseResult confirmInfo(ConfirmInfoDTO confirmInfo);

    List<RecordLevByDateVO> getRecordLevByDate(StatisticalDataByDateDTO statisticalDataByDateDTO);

    List<RecordLevByDateVO> getRecordLevByYearDate(StatisticalDataByDateDTO statisticalDataByDateDTO);

    BasePage<List<MyLinkRecordListVO>> getRecordListByPage(FindRecordsListByParamDTO findRecordsListByParamDTO, Integer pageSize, Integer pageNo);


//    BaseResult download(String imageUrl);

    BaseResult getSelectData();

    BaseResult getRecordInfo(Integer recordId);



}
