package com.aries.jc.lch.modules.test;

import com.aries.jc.lch.modules.utils.YamlUtils;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.*;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lichanghao6
 */
public class Test {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class User {
        private Integer userId;
        private String username;
        private String password;
        private List<String> hobbyList;

        @Override
        public String toString() {
            return
                    "userId=" + userId +
                            ", username='" + username + '\'' +
                            ", password='" + password + '\'' +
                            ", hobbyList=" + hobbyList;
        }


        public Map bean2map(){
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userId);
            map.put("username", username);
            map.put("password", password);
            map.put("hobbyList", hobbyList);
            return map;
        }
    }

    private void downfile(HttpServletResponse response, File file) throws IOException {
        //获取下载的文件路径（注意获取这里获取的是绝对路径，先获取ServletContext再使用ServletContext的getRealPath方法获取绝对路径）
        String fileName = "user1.yaml";
        String path = System.getProperty("user.dir") + "/demo-web/start/src/main/resources/" + fileName;
        //设置响应头控制浏览器以下载的形式打开文件
        response.setHeader("content-disposition", "attachment;fileName=" + "user1.yaml");
        //获取下载文件的输入流
        InputStream in = new FileInputStream(path);
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

    public void javaBean2yaml2() throws IOException {
        User user = User.builder().userId(2).username("张三").password("123456")
                .hobbyList(Arrays.asList("篮球", "足球")).build();
        String fileName = "user1.yaml";
        String path = System.getProperty("user.dir") + "/demo-web/start/src/main/resources/" + fileName;

        // 序列化参数
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        System.out.println(user.toString());
        Yaml yaml = new Yaml(dumperOptions);

        yaml.dump(user, new FileWriter(path));

        // YAMLMapper yamlMapper = new YAMLMapper();
        // String s = yamlMapper.writeValueAsString(user);
        // System.out.println(s);



    }

    public static void main(String[] args) throws IOException {
        new Test().javaBean2yaml2();

    }

}
