package com.aries.jc.lch.modules.test;

import lombok.*;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;

@Component
public class Yaml {


    public void javaBean2yaml2() throws IOException {
        Test.User user = Test.User.builder().userId(2).username("张三").password("123456")
                .hobbyList(Arrays.asList("篮球", "足球")).build();
        String fileName = "user1.yaml";
        String path = System.getProperty("user.dir") + "/demo-web/start/src/main/resources/" + fileName;
        //序列化参数
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml(dumperOptions);
        yaml.dump(user,new FileWriter(path));
        // User u = yaml.loadAs(new FileReader(path), User.class);
        // System.out.println(u);
    }

}
