package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.comon.PageUtils;
import com.itheima.reggie.comon.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.entity.dto.EmployeeDto;
import com.itheima.reggie.entity.vo.EmployeeVO;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        this.save(employee);
        return R.success(employee.getName());
    }

    @Override
    public R page(Integer page, Integer pageSize, String name) {
        Page<Employee> employeePage = new Page<>(page,pageSize);
        Page<EmployeeVO> employeeVOPage = new Page<>(page,pageSize);
        this.baseMapper.selectPage(employeePage,new LambdaQueryWrapper<Employee>()
                .like(StringUtils.isNotEmpty(name),Employee::getName,name)
                .orderByDesc(Employee::getUpdateTime));
        List<EmployeeVO> employeeVOList = new ArrayList<>();
        BeanUtils.copyProperties(employeePage,employeeVOPage);
        employeePage.getRecords().forEach(item->{
            EmployeeVO employeeVO = new EmployeeVO();
            employeeVO.setId(item.getId());
            employeeVO.setName(item.getName());
            employeeVO.setPhone(item.getPhone());
            employeeVO.setStatus(item.getStatus());
            employeeVO.setUsername(item.getUsername());
            employeeVOList.add(employeeVO);

        });
        employeeVOPage.setRecords(employeeVOList);
        return R.success(employeeVOPage);
    }

    @Override
    public R update(HttpServletRequest request, Employee employee) {
        Long eId = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateUser(eId);
        employee.setUpdateTime(LocalDateTime.now());
        this.updateById(employee);
        return R.success(employee.getName());
    }

    @Override
    public R getEmployeeById(Long id) {
        Employee employee = this.getById(id);
        if(employee!=null){
            return R.success(employee);
        }
        return R.error("没有查询到员工信息");
    }


}
