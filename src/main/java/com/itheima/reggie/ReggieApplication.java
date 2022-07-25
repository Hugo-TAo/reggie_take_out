package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author HuJiangTao
 * @create 2022/7/24 22:25
 */
@Slf4j
@SpringBootApplication
//@ComponentScan("com.itheima.reggie.service")
//@ComponentScan("com.itheima.reggie.mapper")
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class,args);
        log.info("项目启动成功...");
    }
}
