package com.aries.jc.lch.modules.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public String uploadFile(@RequestBody MultipartFile file) {
        //创建一个输出流
        OutputStream os = null;
        //创建一个输入流
        InputStream is = null;
        //获得原始文件名（也就是postman传过来的文件的文件名）
        String fileName = file.getOriginalFilename();
        //目标文件的路径
        String path = "D:\\Java\\SpringBoot\\demo\\";
        try {
            //缓冲1kb==>相当于一个中转站==>中转站的大小不是越大越好 也不是越小越好
            byte[] bytes = new byte[1024];

            //声明一个变量 用于记录从输入流读出来的字节个数
            int len;
            //输入流
            is = file.getInputStream();

            //创建一个文件对象==>目标文件
            File tempFile = new File(path);

            //文件对象创建了 但是在物理上不一定有这个文件 文件不存在的话 就创建一个文件夹
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }

            //创建一个输出流==>这个流指向了目标文件
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);//至于为什么这里要用FileOutputStream 我还不知道 反正不能new OutputStream 因为他是一个接口 不能实例化
            //其实 File.separator 的作用相当于 ' \  '
            //在 windows 中 文件文件分隔符 用 ' \ ' 或者 ' / ' 都可以
            //但是在 Linux 中，是不识别 ' \ '  的，而 File.separator 是系统默认的文件分隔符号，在 UNIX 系统上，此字段的值为 ' / '
            //在 Microsoft Windows 系统上，它为 ' \ ' 屏蔽了这些系统的区别。
            //所以用 File.separator 保证了在任何系统下不会出错。

            //持续去读输入流is里面的内容读到中转站里面 如果is里面的内容被读完了 返回值为-1 就结束循环
            while ((len = is.read(bytes)) != -1) {
                //通过输出流把中转站bytes里面的数据写到目标文件中
                os.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //用完后要关闭输入输出流
                os.close();
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "success!";
    }
}