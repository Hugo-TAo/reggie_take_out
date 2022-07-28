package com.itheima.reggie.controller;


import com.itheima.reggie.comon.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    public R add(@RequestBody Category category){
        return categoryService.add(category);
    }

    @GetMapping("/page")
    public R page(Integer page, Integer pageSize){
        return categoryService.page(page,pageSize);
    }

    @DeleteMapping
    public R delete(Long ids){
        return categoryService.delete(ids);
    }

    @PutMapping
    public R update(@RequestBody Category category){
        return categoryService.update(category);
    }

    @GetMapping("/list")
    public R list(Category category){
        return categoryService.list(category);
    }

}
