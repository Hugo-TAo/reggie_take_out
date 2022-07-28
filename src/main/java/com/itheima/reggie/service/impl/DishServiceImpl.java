package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.comon.R;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;
import com.itheima.reggie.entity.dto.DishDto;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.IDishFlavorService;
import com.itheima.reggie.service.IDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {

    @Autowired
    private IDishFlavorService dishFlavorService;

    @Override
    public R add(DishDto dishDto) {
        this.save(dishDto);
        Long dishId =dishDto.getId();
        List<DishFlavor> dishFlavorList = dishDto.getFlavors();
        dishFlavorList.forEach(item->{
            item.setDishId(dishId);
        });
        dishFlavorService.saveBatch(dishFlavorList);
        return R.success(dishDto.getName());
    }

    @Override
    public R page(Integer page, Integer pageSize, String name) {
        Page<Dish> dishPage = new Page<>(page,pageSize);
        this.baseMapper.selectPage(dishPage,new LambdaQueryWrapper<Dish>()
                .like(StringUtils.isNotEmpty(name),Dish::getName,name));
        return R.success(dishPage);
    }
}
