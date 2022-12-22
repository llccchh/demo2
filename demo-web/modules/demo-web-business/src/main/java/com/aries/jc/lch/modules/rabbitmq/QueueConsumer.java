package com.aries.jc.lch.modules.rabbitmq;

import com.aries.jc.lch.modules.test.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author lichanghao6
 * 消费者需要绑定一个topic才能接收消息
 */
@Slf4j
@Component
public class QueueConsumer {

    /**
     * queues表示需要监听的队列
     */
    @RabbitListener(queues = "lchlchlch")
    public void receive(@Payload Student fileBody) {
        log.info("接收到的消息:" + fileBody);
    }
}
