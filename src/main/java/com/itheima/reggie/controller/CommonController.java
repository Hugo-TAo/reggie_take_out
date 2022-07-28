package com.itheima.reggie.controller;

import com.itheima.reggie.comon.R;
import com.itheima.reggie.service.ICommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author HuJiangTao
 * @create 2022/7/28 21:04
 *
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ICommonService commonService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R upload(MultipartFile file){
        return commonService.upload(file);
    }

    /**
     * 文件下载
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        commonService.download(name,response);
    }


}
