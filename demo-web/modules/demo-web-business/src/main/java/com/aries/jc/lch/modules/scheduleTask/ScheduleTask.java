package com.aries.jc.lch.modules.scheduleTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lichanghao6
 */
@Component
public class ScheduleTask {

    /**
     * 需要定时执行的任务
     */
    @Scheduled(cron="${time.cron}")
    void testPlaceholder1() {
        System.out.println("Execute at " + System.currentTimeMillis());
    }
    @Scheduled(cron="*/${time.interval} * * * * *")
    void testPlaceholder2() {
        System.out.println("Execute at " + System.currentTimeMillis());
    }
}
