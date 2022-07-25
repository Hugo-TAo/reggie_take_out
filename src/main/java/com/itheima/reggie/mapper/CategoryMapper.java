package com.itheima.reggie.mapper;

import com.itheima.reggie.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜品及套餐分类 Mapper 接口
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
