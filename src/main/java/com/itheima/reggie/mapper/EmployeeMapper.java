package com.itheima.reggie.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
