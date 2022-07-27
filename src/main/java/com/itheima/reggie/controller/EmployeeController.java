package com.itheima.reggie.controller;


import com.itheima.reggie.comon.PageUtils;
import com.itheima.reggie.comon.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.entity.dto.EmployeeDto;
import com.itheima.reggie.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> Login(HttpServletRequest request,@RequestBody Employee employee) {
        return employeeService.login(request,employee);
    }

    @PostMapping("/logout")
    public R logout(HttpServletRequest request){
        return employeeService.logout(request);
    }

    @PostMapping
    public R add(HttpServletRequest request,@RequestBody EmployeeDto employeeDto){
        return employeeService.add(request,employeeDto);
    }

    @GetMapping("/page")
    public R page(Integer page, Integer pageSize, String name){
        return  employeeService.page(page,pageSize,name);
    }

}
