package com.itheima.reggie.controller;


import com.itheima.reggie.comon.R;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.dto.DishDto;
import com.itheima.reggie.service.IDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author HJT
 * @since 2022-07-25
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private IDishService dishService;

    @PostMapping
    public R add(@RequestBody DishDto dishDto){
        return dishService.add(dishDto);
    }

    @GetMapping("/page")
    public R page(Integer page, Integer pageSize, String name){
        return dishService.page(page,pageSize,name);
    }

}
