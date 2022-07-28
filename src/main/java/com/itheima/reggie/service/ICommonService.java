package com.itheima.reggie.service;

import com.itheima.reggie.comon.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author HuJiangTao
 * @create 2022/7/28 21:04
 */
public interface ICommonService {
    R upload(MultipartFile multipartFile);

    void download(String name, HttpServletResponse response);
}
