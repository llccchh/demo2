package com.hikvision.ga.xpluginmgr.modules.groupc.constant;

import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionParamDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.AddArmingPlanDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.CaptureInfoStorePictureDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.LinkRecordDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ArmingPlan;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.CaptureInfo;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkRecord;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.ChannelMessageVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.DeviceMessageResultVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * bean转换接口
 *
 * @author liuyinghao5
 * @date 2022/11/7 20:55
 */

@Mapper
public interface MyMapperStruct {

    MyMapperStruct INSTANCE = Mappers.getMapper(MyMapperStruct.class);


    /**
     * bean转换
     *
     * @param addArmingPlanDTO 添加布撤防dto
     * @return 返回实体类
     */
//    @Mappings({
//            @Mapping(source = "name", target = "armingPlanName"),
//            @Mapping(source = "linkEventId", target = "eventId"),
//            @Mapping(source = "channelIndexCode", target = "startChannelCode"),
//            @Mapping(source = "linkGroupId", target = "groupId"),
//            @Mapping(source = "timeStrategyId", target = "timeTempId"),
//    })
//    ArmingPlan addArmingPlanDto2ArmingPlan(AddArmingPlanDTO addArmingPlanDTO);


    /**
     *  bean转换
     *
     * @param captureInfoStorePictureDTO dto
     * @return 返回实体类
     */
    CaptureInfo changeDto2Entity(CaptureInfoStorePictureDTO captureInfoStorePictureDTO);

    /**
     * bean转换
     *
     * @param linkRecordDTO dto
     * @return 实体类
     */
    LinkRecord changeLinkRecordDto2Entity(LinkRecordDTO linkRecordDTO);


        @Mappings({
            @Mapping(source = "jsonObject", target = "jsonObject"),
            @Mapping(source = "indexCode", target = "indexCode"),
    })
    ActionParamDTO change (LinkActionVO linkActionVO);



}