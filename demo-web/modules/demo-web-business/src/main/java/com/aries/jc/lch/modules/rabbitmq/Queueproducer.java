package com.aries.jc.lch.modules.rabbitmq;

import com.aries.jc.lch.modules.service.QueueMessageService;
import com.aries.jc.lch.modules.test.Student;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author lichanghao6
 */
@Component
public class Queueproducer {
    @Resource
    private QueueMessageService queueMessageService;

    @Resource
    private Student student;

    /**
     * 每隔5s向rabbitMq的queue里面发送一个数据
     */
    @Scheduled(cron="*/5 * * * * *")
    void testPlaceholder1() {
        //queueMessageService.send("543213245454654","amq.fanout","llllll");//加没加路由键都可以往对应的queue里面发送数据？
        //参数1：交换机名称；参数2：路由键，这里没有使用到路由键，所以为空；参数3：发送的消息内容
        //这里的queueRoutingKey是在queue绑定exchange的时候设置的
        queueMessageService.send("lchexchange", "", student);
    }
}
