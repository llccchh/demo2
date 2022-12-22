package com.aries.jc.lch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * MainClass
 * TODO 请把"Bootstrap"改为组件-服务名的启动类名，如XxcYysApplication,XxcYysApp
 * @author lichanghao6
 */
@SpringBootApplication
@EnableScheduling
public class LchApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(LchApplication.class, args);
  }

}
