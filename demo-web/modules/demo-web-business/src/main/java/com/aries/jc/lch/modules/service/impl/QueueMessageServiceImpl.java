package com.aries.jc.lch.modules.service.impl;

import com.aries.jc.lch.modules.service.QueueMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author lichanghao6
 */
@Slf4j
@Service
public class QueueMessageServiceImpl implements QueueMessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 初始化
     */
    @PostConstruct
    void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void send(String exchange, String queueRoutingKey, Object message) {
        //convertAndSend方法参数1：交换机名称；参数2：路由键，这里没有使用到路由键，所以为空；参数3：发送的消息内容
        rabbitTemplate.convertAndSend(exchange,queueRoutingKey,message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        // 消息由producer发送至exchange返回结果，发送成功时 b为true，失败时为false
        log.info("==========confirm==============:{},{},{}",correlationData,b,s);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        // 消息由exchange传递进入queue过程中，失败时触发
        log.info("===========returnedMessage=============:{},{},{},{},{}",message,i,s,s1,s2);
    }
}
