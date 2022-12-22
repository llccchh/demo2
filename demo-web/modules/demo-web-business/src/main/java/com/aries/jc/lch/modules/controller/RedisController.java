package com.aries.jc.lch.modules.controller;

import com.aries.jc.lch.modules.utils.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisUtils redisUtils;

    @RequestMapping
    public void add() {
        redisUtils.set("llll", "cc");
        System.out.println(redisUtils.get("llll"));
    }

}
