package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikvision.ga.xpluginmgr.modules.groupc.mapper.LinkActionMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ActionDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.AddLinkGroupChannelDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkAction;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.LinkActionVO;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.LinkActionService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liting43
 * @date 2022/11/11 14:02
 * @description
 */
@Service
public class LinkActionServiceImpl extends ServiceImpl<LinkActionMapper, LinkAction> implements LinkActionService {

    @Autowired
    private LinkActionMapper linkActionMapper;

    @Override
    public boolean isNotRelateAction(List<Integer> armingPlanId, List<AddLinkGroupChannelDTO> delChannelDTOList){
        if (ObjectUtils.isEmpty(armingPlanId)) {
            return true;
        }
        LambdaQueryWrapper<LinkAction> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(LinkAction::getArmingPlanId, armingPlanId);
        lambdaQueryWrapper.in(LinkAction::getLinkChannelCode, delChannelDTOList.parallelStream().map(a -> a.getChannelCode()).collect(Collectors.toList()));
        List<LinkAction> linkActions = linkActionMapper.selectList(lambdaQueryWrapper);
        return ObjectUtils.isEmpty(linkActions);
    }

    @Override
    public void deleteAction(Integer armingPlanId){
        LambdaQueryWrapper<LinkAction> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LinkAction::getArmingPlanId, armingPlanId);
        linkActionMapper.delete(lambdaQueryWrapper);
    }

    @Override
    public List<LinkAction> getLinkAction(Integer armingPlanId){
        LambdaQueryWrapper<LinkAction> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LinkAction::getArmingPlanId, armingPlanId);
        lambdaQueryWrapper.orderBy(true, true, LinkAction::getExecuteSequence);
        return linkActionMapper.selectList(lambdaQueryWrapper);
    }

    /**
     * @author liting43
     * 根据规则ID批量删除动作数据
     * @param ids groupId
     */
    @Override
    public void deleteActionBatch(List<Integer> ids){
        if ( ! ObjectUtils.isEmpty(ids)) {
            LambdaQueryWrapper<LinkAction> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(LinkAction::getArmingPlanId, ids);
            linkActionMapper.delete(lambdaQueryWrapper);
        }
    }

    /**
     * 根据起点设备和事件编号确定动作信息
     *
     * @param srcIndexCode 起点设备编码
     * @param eventType    事件类型
     * @return 返回动作需要的参数
     */
    @Override
    public List<LinkActionVO> getOperateBySort(String srcIndexCode, Integer eventType) {
        return linkActionMapper.getActionBySrcIndexCodeAndEventType(srcIndexCode, eventType);
    }

    /**
     * @author liting43
     * 处理并保存联动动作
     */
    @Override
    public void saveAction(List<ActionDTO> linkActionList, Integer armingPlanId){
        List<LinkAction> linkActions = new ArrayList<>();
        Integer size = linkActionList.size();
        if (size.equals(1)){
            linkActions.add(setLinkAction(linkActionList.get(0), armingPlanId, 1));
        }else if (size > 1){
            int i = 1;
            for (ActionDTO action: linkActionList){
                if ("预置点抓图".equals(action.getActionName())){
                    ActionDTO action1 = new ActionDTO();
                    ActionDTO action2 = new ActionDTO();
                    // {“preIndexCode” : "1", "captureInterval":1, "captureTimes":3}
                    JSONObject param = action.getActionParam();
                    JSONObject param1 = new JSONObject();
                    JSONObject param2 = new JSONObject();
                    action1.setActionName("转到预置点");
                    action1.setLinkChannelName(action.getLinkChannelName());
                    action1.setLinkChannelCode(action.getLinkChannelCode());
                    param1.put("preIndexCode", param.getString("preIndexCode"));

                    action2.setActionName("定时抓图");
                    action2.setLinkChannelName(action.getLinkChannelName());
                    action2.setLinkChannelCode(action.getLinkChannelCode());
                    param2.put("captureTime", 0);
                    param2.put("captureInterval", Integer.parseInt(param.getString("captureInterval")));
                    param2.put("captureTimes", Integer.parseInt(param.getString("captureTimes")));

                    action1.setActionParam(param1);
                    action2.setActionParam(param2);

                    linkActions.add(setLinkAction(action1, armingPlanId, i++));
                    linkActions.add(setLinkAction(action2, armingPlanId, i++));
                }else if ("定时抓图".equals(action.getActionName())){
                    linkActions.add(setLinkAction(action, armingPlanId, i++));
                }
            }
            for (ActionDTO action: linkActionList){
                if ("发邮件".equals(action.getActionName())){
                    linkActions.add(setLinkAction(action, armingPlanId, i++));
                }
            }
        }

        saveBatch(linkActions);
    }

    private LinkAction setLinkAction(ActionDTO actionDTO, Integer armingPlanId, Integer exeSeq) {
        LinkAction linkAction = new LinkAction();
        linkAction.setActionName(actionDTO.getActionName());
        linkAction.setArmingPlanId(armingPlanId);
        linkAction.setLinkChannelName(actionDTO.getLinkChannelName());
        linkAction.setLinkChannelCode(actionDTO.getLinkChannelCode());
        linkAction.setActionParam(actionDTO.getActionParam().toString());
        linkAction.setExecuteSequence(exeSeq);
        return linkAction;
    }

    /**
     * @author liting43
     * 获取并处理联动动作
     */
    @Override
    public List<ActionDTO> getActionList(List<LinkAction> linkActions){
        List<ActionDTO> actionList = new ArrayList<>();
        if ("转到预置点".equals(linkActions.get(0).getActionName())){
            ActionDTO action = new ActionDTO();
            action.setActionName("预置点抓图");
            action.setLinkChannelName(linkActions.get(0).getLinkChannelName());
            action.setLinkChannelCode(linkActions.get(0).getLinkChannelCode());
            JSONObject param1 = JSON.parseObject(linkActions.get(0).getActionParam());
            JSONObject param2 = JSON.parseObject(linkActions.get(1).getActionParam());
            JSONObject actionParam = new JSONObject();
            actionParam.put("preIndexCode", param1.getString("preIndexCode"));
            actionParam.put("captureInterval", param2.getString("captureInterval"));
            actionParam.put("captureTimes", param2.getString("captureTimes"));
            action.setActionParam(actionParam);
            actionList.add(action);

            actionList.add(setAction(linkActions.get(2)));
        } else{
            for (LinkAction linkAction: linkActions){
                actionList.add(setAction(linkAction));
            }
        }
        return actionList;
    }

    public ActionDTO setAction(LinkAction linkAction){
        ActionDTO action = new ActionDTO();
        action.setActionName(linkAction.getActionName());
        action.setLinkChannelName(linkAction.getLinkChannelName());
        action.setLinkChannelCode(linkAction.getLinkChannelCode());
        action.setActionParam(JSON.parseObject(linkAction.getActionParam()));
        return action;
    }
}
