package com.hikvision.ga.xpluginmgr.modules.groupc.constant;

import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionParamDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.CaptureInfoStorePictureDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.LinkRecordDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.CaptureInfo;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkRecord;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-03T16:31:06+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
public class MyMapperStructImpl implements MyMapperStruct {

    @Override
    public CaptureInfo changeDto2Entity(CaptureInfoStorePictureDTO captureInfoStorePictureDTO) {
        if ( captureInfoStorePictureDTO == null ) {
            return null;
        }

        CaptureInfo captureInfo = new CaptureInfo();

        captureInfo.setRecordId( captureInfoStorePictureDTO.getRecordId() );
        captureInfo.setChannelIndexCode( captureInfoStorePictureDTO.getChannelIndexCode() );
        captureInfo.setCaptureTime( captureInfoStorePictureDTO.getCaptureTime() );
        captureInfo.setCaptureUrl( captureInfoStorePictureDTO.getCaptureUrl() );

        return captureInfo;
    }

    @Override
    public LinkRecord changeLinkRecordDto2Entity(LinkRecordDTO linkRecordDTO) {
        if ( linkRecordDTO == null ) {
            return null;
        }

        LinkRecord linkRecord = new LinkRecord();

        linkRecord.setArmingPlanId( linkRecordDTO.getArmingPlanId() );
        linkRecord.setEventHappenTime( linkRecordDTO.getEventHappenTime() );

        return linkRecord;
    }

    @Override
    public ActionParamDTO change(LinkActionVO linkActionVO) {
        if ( linkActionVO == null ) {
            return null;
        }

        ActionParamDTO actionParamDTO = new ActionParamDTO();

        actionParamDTO.setIndexCode( linkActionVO.getIndexCode() );
        actionParamDTO.setJsonObject( linkActionVO.getJsonObject() );

        return actionParamDTO;
    }
}
