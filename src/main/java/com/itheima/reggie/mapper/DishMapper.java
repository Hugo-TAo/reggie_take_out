package com.itheima.reggie.mapper;

import com.itheima.reggie.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品管理 Mapper 接口
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
