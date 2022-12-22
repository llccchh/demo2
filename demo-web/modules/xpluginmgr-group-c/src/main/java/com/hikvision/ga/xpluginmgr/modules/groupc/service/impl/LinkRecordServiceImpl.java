package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.aries.jc.common.BaseResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hikvision.ga.xpluginmgr.modules.groupc.constant.MyMapperStruct;
import com.hikvision.ga.xpluginmgr.modules.groupc.mapper.LinkRecordMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.*;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.*;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.*;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkActionService;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkRecordService;
import com.hikvision.ga.xpluginmgr.tool.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuyinghao5
 * @date 2022/11/14 14:58
 */
@Service
public class LinkRecordServiceImpl extends ServiceImpl<LinkRecordMapper, LinkRecord> implements LinkRecordService {


    @Resource
    LinkRecordMapper linkRecordMapper;

    @Resource
    private LinkActionService linkActionService;

    /**
     * 保存联动记录,返回id
     *
     * @param record record
     * @return record_id
     */
    @Override
    public Integer insert(LinkRecordDTO record) {
        LinkRecord linkRecord = MyMapperStruct.INSTANCE.changeLinkRecordDto2Entity(record);
        linkRecordMapper.insert(linkRecord);
        return linkRecord.getId();
    }

    /**
     * @author liting43
     * 确认联动记录报告信息
     * @param confirmInfo 确认信息
     * @return BaseResult
     */
    @Override
    public BaseResult confirmInfo(ConfirmInfoDTO confirmInfo) {
        LinkRecord linkRecord = new LinkRecord();
        linkRecord.setConfirmStatus(1);
        linkRecord.setConfirmInfo(confirmInfo.getConfirmInfo());
        linkRecord.setConfirmOpinion(confirmInfo.getConfirmOpinion());
        linkRecord.setConfirmTime(new Date());

        LambdaUpdateWrapper<LinkRecord> lambdaUpdateWrapper = new LambdaUpdateWrapper();
        lambdaUpdateWrapper.in(LinkRecord::getId, confirmInfo.getIds());
        update(linkRecord, lambdaUpdateWrapper);
        return BaseResult.success(null);
    }

    @Override
    public List<RecordLevByDateVO> getRecordLevByDate(StatisticalDataByDateDTO statisticalDataByDateDTO) {
        return linkRecordMapper.getRecordLevByDate(statisticalDataByDateDTO.getNow(), statisticalDataByDateDTO.getPass());
    }

    @Override
    public List<RecordLevByDateVO> getRecordLevByYearDate(StatisticalDataByDateDTO statisticalDataByDateDTO) {

        return linkRecordMapper.getRecordLevByYearDate(statisticalDataByDateDTO.getNow(), statisticalDataByDateDTO.getPass());
    }



    @Override
    public BasePage<List<MyLinkRecordListVO>> getRecordListByPage(FindRecordsListByParamDTO dto, Integer pageSize, Integer pageNo) {

        PageHelper.startPage(pageNo, pageSize);

        List<MyLinkRecordListVO> list =  linkRecordMapper.selectRecordsList(dto.getStartName(), dto.getGroupName(), dto.getEventType(),
                dto.getEventLevel(),dto.getCheckState());

        PageInfo pageInfo = new PageInfo<>(list);
        list.forEach(a -> a.setHappenTime(strToDateLong(a.getHappenTime())));
        return BasePage.success(list,  pageInfo.getTotal());
    }


    public static String strToDateLong(String strDate) {
        Date date = new Date();
        try {
            //先按照原格式转换为时间
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }


    /**
     * @author liting43
     * 获取查询下拉框数据
     */
    @Override
    public BaseResult getSelectData(){
        SelectDataVO selectDataVO = new SelectDataVO();
        // 获取事件源设备
        List<String> channelCodeName = linkRecordMapper.selectChannelName();
        selectDataVO.setCameraNames(new HashSet<>(channelCodeName));

        // 获取所属分组名称
        List<String> groupName = linkRecordMapper.selectGroupName();
        selectDataVO.setGroupNames(new HashSet<>(groupName));

        // 获取触发事件名称
        List<String> eventName = linkRecordMapper.selectEventName();
        selectDataVO.setEventNames(new HashSet<>(eventName));
        return BaseResult.success(selectDataVO);
    }

    /**
     * @author liting43
     * 获取联动记录详情
     */
    @Override
    public BaseResult getRecordInfo(Integer recordId){

        LinkRecordInfoVO linkRecordInfo = linkRecordMapper.getRecordInfo(recordId);

        switch (linkRecordInfo.getEventLevel()){
            case 1: linkRecordInfo.setEventLevelZh("低");
                break;
            case 2: linkRecordInfo.setEventLevelZh("中");
                break;
            case 3: linkRecordInfo.setEventLevelZh("高");
                break;
            default: break;
        }

        switch (linkRecordInfo.getConfirmStatus()){
            case 1: linkRecordInfo.setConfirmStatusZh("已确认");
                break;
            case 0: linkRecordInfo.setConfirmStatusZh("未确认");
                break;
            default: break;
        }
        linkRecordInfo.setEventHappenTime(strToDateLong(linkRecordInfo.getEventHappenTime()));
        linkRecordInfo.setConfirmTime(linkRecordInfo.getConfirmTime() != null ? strToDateLong(linkRecordInfo.getConfirmTime()) : null);

        // 联动动作
        List<LinkAction> linkActions = linkRecordMapper.selectLinkAction(recordId);
        linkRecordInfo.setActionList(linkActionService.getActionList(linkActions));
        // 抓拍图片
        linkRecordInfo.setCaptureImage(linkRecordMapper.selectCaptureInfo(recordId));

        return BaseResult.success(linkRecordInfo);
    }

}
