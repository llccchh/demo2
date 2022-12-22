package com.hikvision.ga.xpluginmgr.modules.groupc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.AddLinkGroupChannelDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkAction;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liting43
 * @date 2022/11/11 13:59
 * @description
 */
@Service
public interface LinkActionService extends IService<LinkAction> {

    void deleteAction(Integer armingPlanId);

    void saveAction(List<ActionDTO> linkActionList, Integer armingPlanId);

    List<ActionDTO> getActionList(List<LinkAction> linkActions);

    List<LinkAction> getLinkAction(Integer armingPlanId);

    void deleteActionBatch(List<Integer> ids);


    /**
     * 根据起点设备和事件编号确定动作信息
     * @param srcIndexCode  起点设备编码
     * @param eventType 事件类型
     * @return 返回动作需要的参数
     */
    List<LinkActionVO> getOperateBySort(String srcIndexCode, Integer eventType);

    boolean isNotRelateAction(List<Integer> armingPlanId, List<AddLinkGroupChannelDTO> delChannelDTOList);
}
