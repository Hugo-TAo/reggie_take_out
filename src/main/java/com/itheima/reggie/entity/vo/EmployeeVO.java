package com.itheima.reggie.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @author HuJiangTao
 * @create 2022/7/27 12:18
 */
@Data
public class EmployeeVO {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String username;

    private String name;

    private String phone;

    private Integer status;
}
