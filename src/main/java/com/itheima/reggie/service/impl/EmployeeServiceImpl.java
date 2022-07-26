package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.comon.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.entity.dto.EmployeeDto;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {


    @Override
    public R login(HttpServletRequest request, Employee employee) {
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex((password.getBytes()));
        Employee e = this.getOne(new LambdaQueryWrapper<Employee>()
                .eq(Employee::getUsername,employee.getUsername()));
        if(e==null) {
            return R.error("员工不存在");
        }

        if(!e.getPassword().equals(password)) {
            return R.error("密码错误");
        }

        if(e.getStatus()==0){
            return R.error("账号已禁用");
        }

        request.getSession().setAttribute("employee",e.getId());

        return  R.success(e);
    }

    @Override
    public R logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    @Override
    public R add(HttpServletRequest request,EmployeeDto employeeDto) {
        if(this.getOne(new LambdaQueryWrapper<Employee>()
                .eq(Employee::getUsername,employeeDto.getUsername()))!=null){
            R.error("员工已存在");
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        this.save(employee);
        return R.success(employee.getName());
    }




}
