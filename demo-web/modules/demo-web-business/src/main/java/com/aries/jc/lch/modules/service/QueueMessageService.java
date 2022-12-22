package com.aries.jc.lch.modules.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author lichanghao6
 */
public interface QueueMessageService extends RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {
    /**
     * 发送消息到rabbitmq消息队列
     * @param message 消息内容
     * @param exchange 交换配置
     * @param queueRoutingKey routingKey的队列
     *
     */
    void send(String exchange, String queueRoutingKey, Object message);
}
