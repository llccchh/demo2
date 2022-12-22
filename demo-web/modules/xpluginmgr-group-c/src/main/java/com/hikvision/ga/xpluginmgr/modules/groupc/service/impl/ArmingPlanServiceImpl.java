package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hikvision.ga.xpluginmgr.modules.groupc.constant.XpmCameraLinkErrorCode;
import com.hikvision.ga.xpluginmgr.modules.groupc.mapper.ArmingPlanMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.AddArmingPlanDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.ArmingPlanSearchDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.EscEventRouterDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.AddLinkGroupChannelDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.SaveArmingPlanDTO;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ArmingPlan;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkAction;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkEvent;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ReActionAbility;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ReGroupDevice;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.*;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.*;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.SessionUtil;
import com.hikvision.ga.xpluginmgr.tool.utils.PropertiesUtil;
import com.hikvision.hik.crypt.CryptErrorException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class ArmingPlanServiceImpl extends ServiceImpl<ArmingPlanMapper, ArmingPlan> implements ArmingPlanService {

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(ArmingPlanServiceImpl.class);

    @Resource
    private ArmingPlanMapper armingPlanMapper;

    @Resource
    private LinkEventService linkEventService;

    @Resource
    private LinkActionService linkActionService;

    @Resource
    private EscService escService;

    @Resource
    private ReGroupDeviceService reGroupDeviceService;

    @Resource
    private RegionResourceService regionResourceService;

    @Resource
    private ReActionAbilityService reActionAbilityService;

    static PropertiesUtil propertiesUtil = new PropertiesUtil();

    @Override
    public List<Integer> getArmingPlanId(Integer groupId){
        LambdaQueryWrapper<ArmingPlan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArmingPlan::getGroupId, groupId);
        return armingPlanMapper.selectList(lambdaQueryWrapper).parallelStream().map(ArmingPlan::getId).collect(Collectors.toList());
    }

    @Override
    public Boolean isNotRelateArmingPlan(Integer groupId, List<AddLinkGroupChannelDTO> delChannelDTOList) {
        List<String> channelCodes = delChannelDTOList.parallelStream().map(a -> a.getChannelCode()).collect(Collectors.toList());
        LambdaQueryWrapper<ArmingPlan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArmingPlan::getGroupId, groupId);
        lambdaQueryWrapper.in(ArmingPlan::getStartChannelCode, channelCodes);
        List<ArmingPlan> armingPlans = armingPlanMapper.selectList(lambdaQueryWrapper);
        return ObjectUtils.isEmpty(armingPlans);
    }

    /**
     * @author liting43
     * @description 根据分组ID获取联动规则
     *
     * @param ids 分组ID列表
     * @return 联动规则
     */
    @Override
    public List<ArmingPlan> selectByGroupIds(List<Integer> ids) {
        LambdaQueryWrapper<ArmingPlan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(ArmingPlan::getGroupId, ids);
        return armingPlanMapper.selectList(lambdaQueryWrapper);
    }

    /**
     * @author liting43
     * @description 根据规则ID批量删除规则
     *
     * @param ids 规则ID列表
     * @return BaseResult
     */
    @Override
    public BaseResult<Object> delArmingPlan(List<Integer> ids){
        if (ObjectUtils.isEmpty(ids)) {
            return BaseResult.success(null);
        }
        // 1-删除之前：
        // （1）-判断数据是否存在
        List<ArmingPlan> armingPlans = armingPlanMapper.selectBatchIds(ids);
        if(armingPlans.size() != ids.size()){
            return BaseResult.error(XpmCameraLinkErrorCode.ERR_ARMING_PLAN_NOT_EXIST, "Can't delete nonexistent arming plan.");
        }
        // （2）-判断是否启用：查询联动布撤防状态，有布防事件删除失败
        for (ArmingPlan ap: armingPlans){
            if (ap.getArmingPlanStatus().equals(1)){
                return BaseResult.fail(XpmCameraLinkErrorCode.ERR_LINK_GROUP_DEL_FAIL.getCode(), "Can't delete arming plan with deploying status.");
            }
        }

        // 2-删除
        // （1）删除相应联动规则的联动动作
        linkActionService.deleteActionBatch(ids);
        // （2）删除分组内联动规则
        armingPlanMapper.deleteBatchIds(ids);
        return BaseResult.success("Delete arming plans success.");
    }

    /**
     * @author lichanghao6
     * 通过分组id获取联动规则列表
     *
     * @param armingPlanSearchDTO 页码、页面大小、分组id、规则状态、规则名称
     * @return 联动规则列表
     */
    @Override
    public BaseResult getArmingPlanList(ArmingPlanSearchDTO armingPlanSearchDTO) {

        if (armingPlanSearchDTO.getGroupId() == null) {
            return BaseResult.fail(XpmCameraLinkErrorCode.ERR_ARMING_PLAN_NOT_EXIST.getCode(), "Can't find arming plan list, please add groupId.");
        }
        LambdaQueryWrapper<ArmingPlan> lambdaQueryWrapper = Wrappers.lambdaQuery();

        Page<ArmingPlan> page = new Page<>(armingPlanSearchDTO.getPageNum(), armingPlanSearchDTO.getPageSize());

        lambdaQueryWrapper.eq(ArmingPlan::getGroupId, armingPlanSearchDTO.getGroupId());
        lambdaQueryWrapper.eq(armingPlanSearchDTO.getArmingPlanStatus() != null, ArmingPlan::getArmingPlanStatus, armingPlanSearchDTO.getArmingPlanStatus());

        // 根据规则名或创建用户模糊查询
        if (!StringUtils.isEmpty(armingPlanSearchDTO.getSelectInfo())) {
            lambdaQueryWrapper.and(c -> c.like(ArmingPlan::getArmingPlanName, armingPlanSearchDTO.getSelectInfo())
                    .or().like(ArmingPlan::getCreateUser, armingPlanSearchDTO.getSelectInfo()));
        }

        // 分页查询出所有规则列表信息
        Page armingPlans = armingPlanMapper.selectPage(page, lambdaQueryWrapper);
        List<ArmingPlan> list = armingPlans.getRecords();
        List<ArmingPlanVO.ArmingPlanList> armingPlanVOLists = new ArrayList<>();

        for (ArmingPlan group : list) {
            ArmingPlanVO.ArmingPlanList armingPlanVOList = new ArmingPlanVO.ArmingPlanList();
            armingPlanVOList.setId(group.getId());
            armingPlanVOList.setArmingPlanName(group.getArmingPlanName());
            armingPlanVOList.setDescription(group.getDescription());
            armingPlanVOList.setCreateUser(group.getCreateUser());
            armingPlanVOList.setCreateTime(group.getCreateTime());
            armingPlanVOList.setUpdateTime(group.getUpdateTime());
            armingPlanVOList.setArmingPlanStatus(group.getArmingPlanStatus());
            armingPlanVOLists.add(armingPlanVOList);
        }


        ArmingPlanVO armingPlanVO = new ArmingPlanVO();
        armingPlanVO.setTotalNum(list.size());
        armingPlanVO.setArmingPlanList(armingPlanVOLists);

        return BaseResult.success(armingPlanVO);

    }

    /**
     * @author lichanghao6
     * 通过规则id获取联动规则详情
     *
     * @param id 规则id
     * @return 联动规则详情
     */
    @Override
    public BaseResult getArmingPlanInfo(Integer id) {

        ArmingPlanInfoVO armingPlanInfo = armingPlanMapper.getArmingPlanInfo(id);
        List<LinkAction> linkActions = linkActionService.getLinkAction(id);
        if (linkActions.size() > 0){
            armingPlanInfo.setActionList(linkActionService.getActionList(linkActions));
        }

        return BaseResult.success(armingPlanInfo);
    }


    /**
     * @author liting43
     * 添加联动规则
     *
     * @param addArmingPlanDTO 添加的信息
     * @return
     */
    @Override
    public BaseResult<Object> addArmingPlan(AddArmingPlanDTO addArmingPlanDTO) {
        // 配置新增规则数据
        ArmingPlan armingPlan = new ArmingPlan();
        armingPlan.setArmingPlanName(addArmingPlanDTO.getArmingPlanName());
        armingPlan.setGroupId(addArmingPlanDTO.getGroupId());
        armingPlan.setStartChannelName(addArmingPlanDTO.getStartChannelName());
        armingPlan.setStartChannelCode(addArmingPlanDTO.getStartChannelCode());
        armingPlan.setEventId(addArmingPlanDTO.getEventId());
        armingPlan.setEventLevel(addArmingPlanDTO.getEventLevel());
        armingPlan.setTimeTempId(addArmingPlanDTO.getTimeTempId());
        String userId = SessionUtil.getUserId();
        armingPlan.setCreateUser(org.springframework.util.StringUtils.isEmpty(userId) ? "admin" : userId);
        armingPlan.setDescription(addArmingPlanDTO.getDescription());
        // 添加规则数据
        armingPlanMapper.insert(armingPlan);
        // 添加动作数据
        linkActionService.saveAction(addArmingPlanDTO.getLinkActionList(), armingPlan.getId());
        IdVo idVo = new IdVo();
        idVo.setId(armingPlan.getId());
        return BaseResult.success(idVo);
    }

    /**
     * @author liting43
     * 编辑联动规则
     *
     * @param saveArmingPlanDTO 添加的信息
     * @return
     */
    @Override
    public BaseResult<Object> updateArmingPlan(SaveArmingPlanDTO saveArmingPlanDTO){
        // 判断规则启用状态，启用时不能修改
        ArmingPlan armingPlan = armingPlanMapper.selectById(saveArmingPlanDTO.getId());
        if (armingPlan.getArmingPlanStatus() == 1) {
            return BaseResult.error(XpmCameraLinkErrorCode.ERR_ARMING_PLAN_SAVE_FAIL, "Can't update enabled arming plan.");
        }
        // 配置更新数据
        armingPlan.setArmingPlanName(saveArmingPlanDTO.getArmingPlanName());
        armingPlan.setStartChannelName(saveArmingPlanDTO.getStartChannelName());
        armingPlan.setStartChannelCode(saveArmingPlanDTO.getStartChannelCode());
        armingPlan.setEventId(saveArmingPlanDTO.getEventId());
        armingPlan.setEventLevel(saveArmingPlanDTO.getEventLevel());
        armingPlan.setTimeTempId(saveArmingPlanDTO.getTimeTempId());
        armingPlan.setDescription(saveArmingPlanDTO.getDescription());
        armingPlan.setUpdateTime(new Date());
        // 根据规则ID删除联动动作
        linkActionService.deleteAction(armingPlan.getId());
        // 添加动作数据
        linkActionService.saveAction(saveArmingPlanDTO.getLinkActionList(), armingPlan.getId());
        // 更新联动规则数据
        armingPlanMapper.updateById(armingPlan);
        return BaseResult.success(null);
    }

    /**
     * @author liting43
     * 启用 / 禁用规则（布防）
     *
     * @param id 规则ID
     * @return BaseResult
     */
    @Override
    public BaseResult<Object> enableArmingPlan(Integer id) throws CryptErrorException {
        Integer status = armingPlanMapper.selectStatusById(id);
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        // 订阅 / 取消订阅事件：状态为 1 ，禁用规则，取消订阅
        if (status.equals(1)){
            EscEventRouterDTO escEventRouterDTO = setEscEventRouter(ids, 0);
            escService.cancelSubscibeTo(escEventRouterDTO);
            // 修改规则启用状态
            armingPlanMapper.updateArmingPlanStatus(id, 0);
        }else {
            EscEventRouterDTO escEventRouterDTO = setEscEventRouter(ids, 1);
            escService.subscibeTo(escEventRouterDTO);
            armingPlanMapper.updateArmingPlanStatus(id, 1);
        }

        return BaseResult.success(null);
    }

    /**
     * @author liting43
     * 批量启用 / 禁用规则（布防）
     *
     * @param status 修改状态
     * @param ids 规则ID
     * @return BaseResult
     */
    @Override
    public BaseResult<Object> enableArmingPlanBatch(Integer status, List<Integer> ids) throws CryptErrorException {
            if (ids.size() == 0){
                return BaseResult.success(null);
            }
            // 订阅 / 取消订阅事件
            EscEventRouterDTO escEventRouterDTO = setEscEventRouter(ids, status);
            if (status.equals(1)){
                escService.subscibeTo(escEventRouterDTO);
            }else {
                escService.cancelSubscibeTo(escEventRouterDTO);
            }

        // 修改规则启用状态
        updateArmingPlanStatusBatch(ids, status);

        return escService.findEvent();
    }

    /**
     * @author liting43
     * 配置订阅事件参数
     *
     * @param ids 联动规则
     * @return
     */
    private EscEventRouterDTO setEscEventRouter(List<Integer> ids, Integer status) {
        // 配置订阅事件参数
        EscEventRouterDTO escEventRouterDTO = new EscEventRouterDTO();
        // 调用服务的编号
        escEventRouterDTO.setServiceIndex("vssweb");
//        EscEventRouterDTO.EventRouters[] eventRouters= new EscEventRouterDTO.EventRouters[ids.size()];
        List<EscEventRouterDTO.EventRouters> eventRouters = new ArrayList<>();
        // 订阅信息
        for (Integer id: ids){
            ArmingPlan armingPlan = armingPlanMapper.selectById(id);
            // 如果规则的事件已经在订阅中，不再订阅，若已取消订阅，不再取消
            if (armingPlan.getArmingPlanStatus().equals(status)){
                continue;
            }

            EscEventRouterDTO.EventRouters eventRouter = new EscEventRouterDTO.EventRouters();
            // 接收事件的组件或者标识
            String[] serviceType = {"camera-link-id-" + id};
            eventRouter.setDstServiceType(serviceType);
            // 是否根据事件源的事件接收用户进行处理：1-根据事件接收用户附加用户ID  2-不需要根据事件接收用户附加用户，没有用户ID  3-所有用户都可以接收，附加通配符
            eventRouter.setAuthType(2);
            // 事件源编号，起点通道编码，通配符 * 表示所有事件源
            eventRouter.setSrcIndex(armingPlan.getStartChannelCode());
            // 事件类别，如视频事件、门禁事件，通配符*，表示所有事件
            eventRouter.setAbility("*");
            // 事件类型，通配类型 0 表示所有事件类型，如果指定具体的事件类型，ability建议填“*”通配
            eventRouter.setEventType(linkEventService.getEventTypeCode(armingPlan.getEventId()));

            // 获取mq信息
            JSONObject jsonObject = propertiesUtil.getRabbitMQInfo();
            // 事件发送目的URL，建议URL名称能体现事件源和目的
//            eventRouter.setEventDest("rmq://" + jsonObject.getString("rmqHost") + jsonObject.getString("rmqUserName") + jsonObject.getString("rmqPassword"));
            eventRouter.setEventDest("rmq://10.184.49.252:6005/fanout/esc_test?username=root&password=h4Xu0Id6Lr&exchange=esc_test&routerkey=lyh");
            LOGGER.warn(eventRouter.getEventDest());
            // 规则过期时间，秒，最大值7*24*3600秒，0-不过期
            eventRouter.setRuleTimeout(0);
            // 事件超时时间，秒，0-不超时，接收所有符合条件的事件
            eventRouter.setEventTimeout(5);

            eventRouters.add(eventRouter);
        }

        escEventRouterDTO.setEventRouters(eventRouters.toArray(new EscEventRouterDTO.EventRouters[0]));

        return escEventRouterDTO;
    }

    /**
     * @author liting43
     * 修改规则启用状态
     *
     * @param ids 规则ID
     * @param status 规则启用状态
     * @return void
     */
    public void updateArmingPlanStatusBatch(List<Integer> ids, Integer status){
        ArmingPlan armingPlan = new ArmingPlan();
        armingPlan.setArmingPlanStatus(status);
        LambdaUpdateWrapper<ArmingPlan> lambdaUpdateWrapper = new LambdaUpdateWrapper();
        lambdaUpdateWrapper.in(ArmingPlan::getId, ids);
        update(armingPlan, lambdaUpdateWrapper);
    }

    /**
     * @author liting43
     * 获取添加规则时展示的当前分组内的设备信息
     *
     * @param groupId 分组ID
     * @param selectInfo 查询信息
     * @param deviceType 设备类型
     * @return 添加规则所属分组的设备和通道信息
     */
    @Override
    public BaseResult<Object> getDevice(Integer groupId, String selectInfo, String deviceType){
        List<ReGroupDevice> reGroupDeviceList = reGroupDeviceService.selectByGroupId(groupId);
        // 获取当前分组内已关联的设备
        List<String> deviceIndexCode = reGroupDeviceList.parallelStream().map(ReGroupDevice::getDeviceIndexCode).collect(Collectors.toList());
        // 获取当前分组内已关联的通道
        List<String> channelIndexCode = reGroupDeviceList.parallelStream().map(ReGroupDevice::getChannelCode).collect(Collectors.toList());
        // 根据设备编码查询设备及其通道信息
        List<DeviceMessageResultVO> deviceMessageResultVOS = regionResourceService.getDeviceMessageByRegion("", deviceIndexCode, null, regionResourceService.getRegionMessageMap(), null).getData();

        // 筛选分组内已关联的通道
        for (DeviceMessageResultVO deviceMessageResult: deviceMessageResultVOS){
            deviceMessageResult.setChannelInfo(deviceMessageResult.getChannelInfo().stream().filter(a ->
                    channelIndexCode.contains(a.getChannelCode())).collect(Collectors.toList()));
        }

        // 模糊筛选数据
        if (StringUtils.isNotEmpty(selectInfo) || StringUtils.isNotEmpty(deviceType)){
            deviceMessageResultVOS = deviceMessageResultVOS.stream().filter(a -> {
                String pattern = ".*?" + selectInfo + ".*?";
                if (StringUtils.isNotEmpty(selectInfo)){
                    // 必须判断是否为空，否则，如果该参数为空，下面的if必为true，而第二个参数有值却并不会判断了
                    if (Pattern.matches(pattern, a.getDeviceName()) || Pattern.matches(pattern, a.getDeviceCode())){
                        return true;
                    }
                }else if (deviceType.equals(a.getDeviceType())){
                    // 不需判断是否为空，第一个参数为空，第二个参数则不为空
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
        }

        // 赋值并获取事件能力集和动作能力集
        List<ArmingPlanGetDeviceVO> armingPlanGetDeviceVOS = deviceMessageResultVOS.stream().map(a -> {
            ArmingPlanGetDeviceVO armingPlanGetDevice = ArmingPlanGetDeviceVO.getDeviceData(a);
            List<LinkEvent> linkEvents = linkEventService.getDeviceLinkEventList(armingPlanGetDevice.getDeviceIndexCode()).getData();
            List<ReActionAbility> reActionAbilities = reActionAbilityService.getDeviceActionList(armingPlanGetDevice.getDeviceIndexCode()).getData();
            armingPlanGetDevice.setEventCapability(linkEvents.stream().map(LinkEvent::getEventName).collect(Collectors.toList()));
            armingPlanGetDevice.setActionCapability(reActionAbilities.stream().map(ReActionAbility::getActionName).collect(Collectors.toList()));
            return armingPlanGetDevice;
        }).collect(Collectors.toList());

        return BaseResult.success(armingPlanGetDeviceVOS);
    }

    @Override
    public Integer getArmingPlanCount(Integer groupId){
        LambdaQueryWrapper<ArmingPlan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArmingPlan::getGroupId, groupId);
        return armingPlanMapper.selectList(lambdaQueryWrapper).size();
    }

    @Override
    public Boolean getArmingPlanState(Integer groupId) {
        LambdaQueryWrapper<ArmingPlan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArmingPlan::getGroupId, groupId);
        lambdaQueryWrapper.eq(ArmingPlan::getArmingPlanStatus, 1);
        List<ArmingPlan> armingPlan = armingPlanMapper.selectList(lambdaQueryWrapper);
        return ObjectUtils.isEmpty(armingPlan);
    }


    /**
     * 根据时间模板id查找对应状态的id
     *
     *
     * @return 返回id列表
     */
    @Override
    public List<Integer> getMessageByTime(Integer timeTemId, Integer state) {
        LambdaQueryWrapper<ArmingPlan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArmingPlan::getTimeTempId, timeTemId).eq(ArmingPlan::getArmingPlanStatus, state);
        List<ArmingPlan> list = baseMapper.selectList(lambdaQueryWrapper);
        return list.stream().map(ArmingPlan::getId).collect(Collectors.toList());
    }

}
