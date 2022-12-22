package com.aries.jc.lch.modules.service.impl;

import com.aries.jc.common.BaseResult;
import com.aries.jc.lch.modules.repository.entity.User;
import com.aries.jc.lch.modules.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import kotlin.sequences.USequencesKt;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

/**
 * @author lichanghao6
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public void javaBean2yaml2(HttpServletResponse response) throws IOException {


        // User user = User.builder().userId(2).username("张三").password("123456")
        //          .build();
        // String fileName = "user1.yaml";
        // String path = System.getProperty("user.dir") + "/demo-web/start/src/main/resources/" + fileName;
        // File file = new File(path);
        // //序列化参数
        // DumperOptions dumperOptions = new DumperOptions();
        // dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        // Yaml yaml = new Yaml(dumperOptions);
        // yaml.dump(user.bean2map(), new FileWriter(file));
        // downfile(response, file);
        // return BaseResult.success(null);

        User user = descriptionInfo().getData();

        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        String fileName = user.getUsername() + ".yml";
        // String fileName = "user1.yaml";
        String path = System.getProperty("user.dir") + "/demo-web/start/src/main/resources/" + fileName;
        File file = new File(path);
        // 创建FileWriter
        FileWriter fileWriter = new FileWriter(path);
        // 创建yaml类
        //YAML类是API的入口点
        Yaml yaml = new Yaml(dumperOptions);
        // //yml文件使用 LinkedHashMap来存储的
        // LinkedHashMap<String, Object> ymlMap = new LinkedHashMap<>();
        // //使用put方法添加内容
        // ymlMap.put("userId", 2);
        // ymlMap.put("username", "张三");
        // ymlMap.put("password", "123456");
        //dump方法生成yaml
        yaml.dump(user.bean2map(), fileWriter);
        downfile(response, file, fileName);
    }

    private void downfile(HttpServletResponse response, File file, String fileName) throws IOException {

        //设置响应头控制浏览器以下载的形式打开文件
        response.setHeader("content-disposition", "attachment;fileName=" + fileName);
        //获取下载文件的输入流
        InputStream in = new FileInputStream(file);
        int count = 0;
        byte[] by = new byte[1024];
        //通过response对象获取OutputStream流
        OutputStream out = response.getOutputStream();
        while ((count = in.read(by)) != -1) {
            //将缓冲区的数据输出到浏览器
            out.write(by, 0, count);
        }
        in.close();
        out.flush();
        out.close();
    }

    public BaseResult<User> descriptionInfo() {

        User user = new User();

        user.setUserId(1);
        user.setUsername("lch");
        user.setPassword("111");

        return BaseResult.success(user);
    }
}
