package com.hikvision.ga.xpluginmgr.modules.groupc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aries.jc.common.BaseResult;
import com.aries.jc.common.factory.AriesJcLoggerFactory;
import com.aries.jc.common.log.AriesJcLogger;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hikvision.ga.xpluginmgr.modules.groupc.constant.XpmCameraLinkErrorCode;
import com.hikvision.ga.xpluginmgr.modules.groupc.mapper.LinkGroupMapper;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto.*;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ArmingPlan;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.LinkGroup;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.entity.ReGroupDevice;
import com.hikvision.ga.xpluginmgr.modules.groupc.repository.vo.*;
import com.hikvision.ga.xpluginmgr.modules.groupc.service.*;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.JsonUtils;
import com.hikvision.ga.xpluginmgr.modules.groupc.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author lichanghao6
 */
@Service
public class LinkGroupServiceImpl implements LinkGroupService {

    private static final AriesJcLogger LOGGER = AriesJcLoggerFactory.getLogger(LinkGroupServiceImpl.class);

    @Autowired
    private LinkGroupMapper linkGroupMapper;

    @Autowired
    private ReGroupDeviceService reGroupDeviceService;

    @Autowired
    private ArmingPlanService armingPlanService;

    @Autowired
    private LinkActionService linkActionService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private RegionResourceService regionResourceService;

    /**
     * @author liting43
     * @description 添加、编辑联动分组
     * @param addLinkGroupDTO
     * return BaseResult
     */
    @Override
    public BaseResult saveLinkGroup(AddLinkGroupDTO addLinkGroupDTO) {
        // 1-配置基本信息
        LinkGroup linkGroup = new LinkGroup();
        linkGroup.setGroupName(addLinkGroupDTO.getGroupName());
        linkGroup.setDescription(addLinkGroupDTO.getDescription());
        String userId = SessionUtil.getUserId();
        linkGroup.setCreateUser(StringUtils.isEmpty(userId) ? "admin" : userId);

        // 2-判断是添加 / 编辑
        BaseResult result = new BaseResult();
        Integer id = addLinkGroupDTO.getId();
        if(id == null){
            // id 为空，添加数据
            linkGroupMapper.insert(linkGroup);
            result = addChannelInLinkGroup(addLinkGroupDTO.getChannelList(), linkGroup.getId());
        }else {
            // id 不为空，编辑数据
            // 更新联动分组与通道的关系
            result = updateReGroupDevice(addLinkGroupDTO.getChannelList(), id);
            if (! result.isSuccess()){
                return result;
            }

            // 更新联动分组数据
            linkGroup.setId(id);
            linkGroupMapper.updateById(linkGroup);
        }
        return result;
    }

    /**
     * @author liting43
     * @description 更新联动分组与通道的关系
     * @param channelDTOList
     * @param groupId
     * return void
     */
    public BaseResult updateReGroupDevice(List<AddLinkGroupChannelDTO> channelDTOList, Integer groupId){
        // 1-获取分组下已有的通道编码数据
        List<ReGroupDevice> reGroupDeviceList = reGroupDeviceService.selectByGroupId(groupId);
        List<AddLinkGroupChannelDTO> linkGroupChannelDTOS = reGroupDeviceList.parallelStream().map(AddLinkGroupChannelDTO::getData).collect(Collectors.toList());
        // 2-判断是否需要更新分组与通道的关系
        if(!equalList(channelDTOList, linkGroupChannelDTOS)){
            // 2.1-筛选出新增的和要删除的分组与通道关系
            List<AddLinkGroupChannelDTO> newChannelDTOList = filterReDeviceChannel(channelDTOList, linkGroupChannelDTOS);
            List<AddLinkGroupChannelDTO> delChannelDTOList = filterReDeviceChannel(linkGroupChannelDTOS, channelDTOList);

            // 2.2-判断是否需要删除分组与通道关系
            if ( ! ObjectUtils.isEmpty(delChannelDTOList)) {
                // 判断要删除的通道是否绑定联动规则或执行动作，有绑定的，不能删除，直接报错返回提示信息
                List<Integer> armingPlanId = armingPlanService.getArmingPlanId(groupId);
                if(armingPlanService.isNotRelateArmingPlan(groupId, delChannelDTOList) && linkActionService.isNotRelateAction(armingPlanId, delChannelDTOList)) {
                    // 起点通道无联动规则，联动通道无执行动作，通道与分组关系可删除
                    reGroupDeviceService.deleteByGroupIdAndBatchChannelCode(groupId,
                            delChannelDTOList.parallelStream().map(a -> a.getChannelCode()).collect(Collectors.toList()));
                }else {
                    LOGGER.errorWithErrorCode(XpmCameraLinkErrorCode.ERR_LINK_GROUP_EDIT_FAIL.getCode(),
                            "Can't edit link group with device bonded by link rules!");
                    return BaseResult.error(XpmCameraLinkErrorCode.ERR_LINK_GROUP_EDIT_FAIL.getCode(),
                            "Can't edit link group with device bonded by link rules! Please check again before editing!");
                }
            }
            // 2.3-添加新增的通道关系
            addChannelInLinkGroup(newChannelDTOList, groupId);
        }
        return BaseResult.success(null);
    }

    private List<AddLinkGroupChannelDTO> filterReDeviceChannel(List<AddLinkGroupChannelDTO> channelDTOListA, List<AddLinkGroupChannelDTO> channelDTOListB) {
        List<AddLinkGroupChannelDTO> channelDTOList = channelDTOListA.parallelStream().filter(a -> {
                for (AddLinkGroupChannelDTO channelDTO: channelDTOListB){
                    if (channelDTO.getChannelCode().equals(a.getChannelCode())){
                        return false;
                    }
                }
                return true;
            }).collect(Collectors.toList());
        return channelDTOList;
    }

    /**
     * @author liting43
     * @description 判断两个list是否相等
     * @param listA
     * @param listB
     * return boolean
     */
    public boolean equalList(List<AddLinkGroupChannelDTO> listA, List<AddLinkGroupChannelDTO> listB){
        if (listA.size() == listB.size()){
            return isEqual(listA, listB) && isEqual(listB, listA);
        }
        return false;
    }

    private Boolean isEqual(List<AddLinkGroupChannelDTO> listA, List<AddLinkGroupChannelDTO> listB) {
        List<AddLinkGroupChannelDTO> channelDTOList = listA.parallelStream().filter(a -> {
            boolean flag = true;
            for (AddLinkGroupChannelDTO b: listB){
                if (a.getChannelCode().equals(b.getChannelCode()) && a.getDeviceIndexCode().equals(b.getDeviceIndexCode())){
                    flag = false;
                    break;
                }
            }
            return flag;
        }).collect(Collectors.toList());
        return ObjectUtils.isEmpty(channelDTOList);
    }

    /**
     * @author liting43
     * @description 添加联动分组与设备、通道的关联关系
     * @param channelList
     * @param groupId
     * return BaseResult
     */
    public BaseResult addChannelInLinkGroup(List<AddLinkGroupChannelDTO> channelList, Integer groupId) {
        List<ReGroupDevice> reGroupDeviceList = new ArrayList<>();
        for (AddLinkGroupChannelDTO channel : channelList) {
            ReGroupDevice reGroupDevice = new ReGroupDevice();
            reGroupDevice.setGroupId(groupId);
            reGroupDevice.setChannelCode(channel.getChannelCode());
            reGroupDevice.setDeviceIndexCode(channel.getDeviceIndexCode());
            reGroupDevice.setCreateTime(new Timestamp(System.currentTimeMillis()));
            reGroupDevice.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            reGroupDeviceList.add(reGroupDevice);
        }
        boolean flag = reGroupDeviceService.insertBatch(reGroupDeviceList);
        return flag ? BaseResult.success(null) :
                BaseResult.error(XpmCameraLinkErrorCode.ERR_CHANNEL_ADD_FAIL.getCode(), "Save relation between link group and channel fail");
    }

    /**
     * @author liting43
     * @description 批量删除联动分组
     * @param batchIdDTO
     * return BaseResult
     */
    @Override
    public BaseResult delLinkGroup(BatchIdDTO batchIdDTO){
        List<Integer> ids = batchIdDTO.getIds();
        // 1-删除之前：
        // （1）-判断数据是否存在
        List<LinkGroup> linkGroups = linkGroupMapper.selectBatchIds(ids);
        if(linkGroups.size() != ids.size()){
            return BaseResult.error(XpmCameraLinkErrorCode.ERR_LINK_GROUP_NOT_EXIST, "Can't delete nonexistent link group.");
        }
        // （2）-判断分组内布防情况：查询联动布撤防状态，有布防事件删除失败
        List<ArmingPlan> armingPlans = armingPlanService.selectByGroupIds(ids);
        List<Integer> armingPlanStatus = armingPlans.parallelStream().map(ArmingPlan::getArmingPlanStatus).collect(Collectors.toList());
        if(armingPlanStatus.contains(1)){
            return BaseResult.fail(XpmCameraLinkErrorCode.ERR_LINK_GROUP_DEL_FAIL.getCode(), "Can't delete link group with deploying arming plan.");
        }

        // 2-删除
        // （1）删除分组内联动规则
        List<Integer> apIds = armingPlans.parallelStream().map(ArmingPlan::getId).collect(Collectors.toList());
        armingPlanService.delArmingPlan(apIds);
        // （2）删除分组内的通道信息数据
        reGroupDeviceService.deleteChannelInGroupId(ids);
        // （3）删除联动分组数据
        linkGroupMapper.deleteBatchIds(ids);
        return BaseResult.success("Delete link group success.");
    }


    /**
     * @author lichanghao6
     * @description 获取分组列表
     * @param groupSearchDTO 搜索参数
     * @return 分组列表
     */
    @Override
    public BaseResult getGroupList(GroupSearchDTO groupSearchDTO) {

        LambdaQueryWrapper<LinkGroup> lambdaQueryWrapper = Wrappers.lambdaQuery();

        Page<LinkGroup> page = new Page<>(groupSearchDTO.getPageNum(), groupSearchDTO.getPageSize());
        LOGGER.trace("getGroupList >>> param is {}", JsonUtils.object2Json(groupSearchDTO));

        // 根据分组名称或创建用户筛选
        if (!StringUtils.isEmpty(groupSearchDTO.getSelectInfo())) {
            lambdaQueryWrapper.and(c -> c.like(LinkGroup::getGroupName, groupSearchDTO.getSelectInfo())
                    .or().like(LinkGroup::getCreateUser, groupSearchDTO.getSelectInfo()));
        }

        // 分页查询出所有分组列表信息
        Page linkGroups = linkGroupMapper.selectPage(page, lambdaQueryWrapper);
        List<LinkGroup> list = linkGroups.getRecords();
        List<LinkGroupVO.LinkGroupList> linkGroupVOLists = new ArrayList<>();

        // 查询每个分组下的联动规则数，与其他参数一起放入到LinkGroupVO中的内部类LinkGroupList中
        for (LinkGroup group : list) {
            LinkGroupVO.LinkGroupList linkGroupVOList = new LinkGroupVO.LinkGroupList();
            linkGroupVOList.setId(group.getId());
            linkGroupVOList.setGroupName(group.getGroupName());
            linkGroupVOList.setDescription(group.getDescription());
            linkGroupVOList.setCreateUser(group.getCreateUser());
            linkGroupVOList.setCreateTime(group.getCreateTime());
            linkGroupVOList.setUpdateTime(group.getUpdateTime());
            linkGroupVOList.setArmingPlanCount(armingPlanService.getArmingPlanCount(group.getId()));
            linkGroupVOList.setIsDeleteAbel(armingPlanService.getArmingPlanState(group.getId()));
            linkGroupVOLists.add(linkGroupVOList);
        }

        LinkGroupVO linkGroupVO = new LinkGroupVO();
        linkGroupVO.setTotalNum(list.size());
        linkGroupVO.setLinkGroupList(linkGroupVOLists);

        return BaseResult.success(linkGroupVO);
    }


    /**
     * @author lichanghao6
     * @description 获取分组详情
     * @param id 分组id
     * @return 分组列表详情
     */
    @Override
    public BaseResult getGroupInfo(Integer id) {

        // 从联动分组表中获取分组id，名称和描述
        GetGroupInfoVO groupBaseInfo = linkGroupMapper.getGroupInfo(id);

        // 获取联动分组内的设备编码列表
        List<String> deviceIndexCode = linkGroupMapper.getGroupDeviceCodeById(id);

        // getDeviceMessageByRegion，通过设备编码device_index_code获取设备及通道信息，并存至GetGroupInfoVO中
        groupBaseInfo.setChannelMessageList(deviceService.getChannelByDeviceIndexCode(deviceIndexCode));
        List<DeviceMessageResultVO> list = regionResourceService.getDeviceMessageByRegion("", deviceIndexCode, null, regionResourceService.getRegionMessageMap(), null).getData();
        Map<String, String> nameMap = new HashMap<>();

        for (DeviceMessageResultVO vo : list) {
            nameMap.put(vo.getDeviceCode(), vo.getDeviceName());
        }
        for (ChannelMessageVO vo : groupBaseInfo.getChannelMessageList()) {
            vo.setBelongDeviceName(nameMap.get(vo.getDeviceIndexCode()));
        }
        return BaseResult.success(groupBaseInfo);

    }

    /**
     * @author lichanghao6
     * @description 获取分组设备列表
     * @param deviceDTO 搜索参数
     * @return 分组内设备列表
     */
    @Override
    public BaseResult getGroupDeviceList(DeviceDTO deviceDTO) {

        // 获取联动分组内的设备编码列表
        List<String> deviceCode = linkGroupMapper.getGroupDeviceCodeById(deviceDTO.getGroupId());

        // 根据设备编码查询设备及其通道信息
        List<DeviceMessageResultVO> deviceMessageResultVOS = regionResourceService.getDeviceMessageByRegion("", deviceCode, null, regionResourceService.getRegionMessageMap(), null).getData();

        // 通过设备名称/区域模糊筛选数据，这里面是或，两个满足一个就会返回
        if (!StringUtils.isEmpty(deviceDTO.getSelectInfo())){
            deviceMessageResultVOS = deviceMessageResultVOS.stream().filter(a -> findBySelectInfo(deviceDTO.getSelectInfo() ,a.getDeviceName())
                    || findBySelectInfo(deviceDTO.getSelectInfo(), a.getRegion()) ).collect(Collectors.toList());
        }

        // 对deviceList分页
        List<JSONObject> deviceInfoList = new ArrayList<>();
        for (DeviceMessageResultVO deviceMes : deviceMessageResultVOS) {
            deviceInfoList.add((JSONObject) JSONObject.toJSON(deviceMes));
        }

        List<JSONObject> data = new ArrayList<>();
        if (!deviceInfoList.isEmpty()) {
            data = pageBySubList(deviceInfoList, deviceDTO.getPageSize(), deviceDTO.getPageNum());
        }

        // JSONObject dataJson = new JSONObject();
        // dataJson.put("totalNum", deviceMessageResultVOS.size());
        // dataJson.put("deviceInfo", data);
        // return BaseResult.success(dataJson);

        DeviceListVO deviceListVO = new DeviceListVO();
        deviceListVO.setTotalNum(deviceMessageResultVOS.size());
        deviceListVO.setDeviceInfo(data);

        return BaseResult.success(deviceListVO);
    }

    /**
     * 模糊查询：
     * . 代表一个字符
     * * 代表有任意个前面的符号代表的东西，如字符
     * ？表示这些字符可有可无
     */
    private boolean findBySelectInfo(String selectInfo, String res){
        String pattern = ".*?" + selectInfo + ".*?";

        return Pattern.matches(pattern, res);
    }

    /**
     * 利用subList方法进行分页
     *
     * @param list        分页数据
     * @param pageSize    页面大小
     * @param pageNum     当前页面
     */
    public static List<JSONObject> pageBySubList(List<JSONObject> list, int pageSize, int pageNum) {
        int totalCount = list.size();
        int pageCount = 0;
        List<JSONObject> subList;
        int m = totalCount % pageSize;
        if (m > 0) {
            pageCount = totalCount / pageSize + 1;
        } else {
            pageCount = totalCount / pageSize;
        }
        if (m == 0) {
            subList = list.subList((pageNum - 1) * pageSize, pageSize * (pageNum));
        } else {
            if (pageNum == pageCount) {
                subList = list.subList((pageNum - 1) * pageSize, totalCount);
            } else {
                subList = list.subList((pageNum - 1) * pageSize, pageSize * (pageNum));
            }
        }
        return subList;
    }

    /**
     * @author liting43
     * 修改联动分组名称或描述
     * @param id 联动分组ID
     * @param name 联动分组名称
     * @param description 联动分组描述
     * @return BaseResult
     */
    @Override
    public BaseResult save(Integer id, String name, String description){
        LinkGroup linkGroup = linkGroupMapper.selectById(id);
        if ( ! StringUtils.isEmpty(name)){
            linkGroup.setGroupName(name);
        }
        if ( ! StringUtils.isEmpty(description)){
            linkGroup.setDescription(description);
        }
        linkGroupMapper.updateById(linkGroup);
        return BaseResult.success(null);
    }
}
