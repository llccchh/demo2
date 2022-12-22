package com.hikvision.ga.xpluginmgr.modules.groupc.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyinghao5
 */


@Configuration
public class FanoutConfig {

    /**
     * 新增区域交换机
     * @return Fanout类型交换机
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("bic.ldap.topic.resource_change");
    }


    /**
     * 新增区域更新监听队列
     */
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("groupc.region.listener");
    }


    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }
}

