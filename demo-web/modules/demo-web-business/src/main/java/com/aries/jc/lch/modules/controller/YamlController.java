package com.aries.jc.lch.modules.controller;

import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lichanghao6
 */
@RestController
@RequestMapping("/yaml")
public class YamlController {

    @Resource
    UserService userService;

    @RequestMapping
    public void javaBean2yaml2 (HttpServletResponse response) throws IOException {

        userService.javaBean2yaml2(response);
        // return BaseResult.success(null);
    }
}
