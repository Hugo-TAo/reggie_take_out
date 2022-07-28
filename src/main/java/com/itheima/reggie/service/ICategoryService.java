package com.itheima.reggie.service;

import com.itheima.reggie.comon.R;
import com.itheima.reggie.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜品及套餐分类 服务类
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
public interface ICategoryService extends IService<Category> {
    R add(Category category);

    R page(Integer page, Integer pageSize);

    R delete(Long ids);

    R update(Category category);

    R list(Category category);
}
