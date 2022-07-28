package com.itheima.reggie.service.impl;

import com.itheima.reggie.comon.R;
import com.itheima.reggie.service.ICommonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @author HuJiangTao
 * @create 2022/7/28 21:05
 */
@Service
public class CommonServiceImpl implements ICommonService {

    @Value("${reggie.path}")
    private String basePath;

    @Override
    public R upload(MultipartFile file) {
        //原始文件名
        String originalFileName = file.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        //UUID文件名
        String fileName = UUID.randomUUID().toString() + suffix;
        File dir = new File(basePath);
        //判断目录是否存在
        if(!dir.exists()){
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    @Override
    public void download(String name, HttpServletResponse response) {
        try {
            //输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(basePath + name);

            //输出流将文件写回浏览器
            ServletOutputStream servletOutputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int length = 0;
            byte[] bytes = new byte[1024];
            while ((length = fileInputStream.read(bytes)) != -1){
                servletOutputStream.write(bytes,0,length);
                servletOutputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
