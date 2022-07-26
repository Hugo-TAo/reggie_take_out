package com.itheima.reggie.service;

import com.itheima.reggie.comon.PageUtils;
import com.itheima.reggie.comon.R;
import com.itheima.reggie.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.dto.EmployeeDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 员工信息 服务类
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
public interface IEmployeeService extends IService<Employee> {

    R login(HttpServletRequest request,Employee employee);

    R logout(HttpServletRequest request);

    R add(HttpServletRequest request,EmployeeDto employeeDto);

    R page(Integer page, Integer pageSize, String name);

    R update(HttpServletRequest request,Employee employee);

    R getEmployeeById(Long id);
}
