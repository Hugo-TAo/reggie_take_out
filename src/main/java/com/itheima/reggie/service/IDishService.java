package com.itheima.reggie.service;

import com.itheima.reggie.comon.R;
import com.itheima.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.dto.DishDto;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
public interface IDishService extends IService<Dish> {

    R add(DishDto dishDto);

    R page(Integer page, Integer pageSize, String name);
}
