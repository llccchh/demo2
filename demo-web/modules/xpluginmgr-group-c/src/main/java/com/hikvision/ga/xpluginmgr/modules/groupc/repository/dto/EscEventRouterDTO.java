package com.hikvision.ga.xpluginmgr.modules.groupc.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * @author liuyinghao5
 * @date 2022/11/16 9:39
 */

public class EscEventRouterDTO {

    /**
     * 调用服务的编号
     */

    private String serviceIndex;
    private EventRouters[] eventRouters;


    public EscEventRouterDTO(String serviceIndex, EventRouters[] eventRouters) {
        this.serviceIndex = serviceIndex;
        this.eventRouters = eventRouters;
    }

    public EscEventRouterDTO() {
    }

    public String getServiceIndex() {
        return serviceIndex;
    }

    public void setServiceIndex(String serviceIndex) {
        this.serviceIndex = serviceIndex;
    }

    public EventRouters[] getEventRouters() {
        return eventRouters;
    }

    public void setEventRouters(EventRouters[] eventRouters) {
        this.eventRouters = eventRouters;
    }

    @Override
    public String toString() {
        return "EscEventRouterDTO{" +
                "serviceIndex='" + serviceIndex + '\'' +
                ", eventRouters=" + Arrays.toString(eventRouters) +
                '}';
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EventRouters {





        /**
         * 接收事件的组件或者标识，小写，订阅规则完全相同的才能合并
         */
        private String[] dstServiceType;

        /**
         * 是否根据事件源的事件接收用户进行处理 1-根据事件接收用户附加用户ID  2-不需要根据事件接收用户附加用户，没有用户ID，一般适用后台服务  3-所有用户都可以接收，附加通配符
         */
        private Integer authType;

        /**
         * 事件源类型，可选
         */
        private String srcType;

        /**
         * 事件源编号 *为所有
         */
        private String srcIndex;

        /**
         * 事件类别，* 表示所有
         */
        private String ability;

        /**
         * 事件类型  0， 表示所有，如果表示具体的事件类型，ability建议填*
         */
        private Integer eventType;

        /**
         * 事件超时时间 秒 0位不超时
         */
        private Integer eventTimeout;

        /**
         * url
         */
        private String eventDest;

        /**
         * 规则过期时间
         */
        private Integer ruleTimeout;



    }

}
