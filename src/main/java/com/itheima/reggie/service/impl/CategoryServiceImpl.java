package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.comon.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.entity.SetmealDish;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.service.IDishService;
import com.itheima.reggie.service.ISetmealDishService;
import com.itheima.reggie.service.ISetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private IDishService dishService;

    @Autowired
    private ISetmealService setmealService;

    @Override
    public R add(Category category) {
        this.save(category);
        return R.success(category);
    }

    @Override
    public R page(Integer page, Integer pageSize) {
        Page<Category> categoryPage = new Page<>(page,pageSize);
        this.baseMapper.selectPage(categoryPage,new LambdaQueryWrapper<Category>()
                .orderByDesc(Category::getUpdateTime));
        return R.success(categoryPage);
    }

    @Override
    public R delete(Long ids) {
        if(dishService.getBaseMapper().selectCount(new LambdaQueryWrapper<Dish>().eq(Dish::getCategoryId,ids))!=0){
            return R.error("当前分类关联了菜品不能删除");
        }
        if(setmealService.getBaseMapper().selectCount(new LambdaQueryWrapper<Setmeal>().eq(Setmeal::getCategoryId,ids))!=0){
            return R.error("当前套餐关联了菜品不能删除");
        }
        this.removeById(ids);
        return R.success(ids);
    }

    @Override
    public R update(Category category) {
        this.updateById(category);
        return R.success("修改成功");
    }
}
