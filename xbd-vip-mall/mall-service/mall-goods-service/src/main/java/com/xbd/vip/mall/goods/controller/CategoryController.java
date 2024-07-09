package com.xbd.vip.mall.goods.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.goods.model.Category;
import com.xbd.vip.mall.goods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

/*
根据分类父id查询子分类
 */
    @GetMapping(value = "/parent/{pid}")
    public RespResult<List<Category>> list(@PathVariable(value = "pid")Integer pid){
        List<Category> categories = categoryService.queryByParentId(pid);
        return RespResult.ok(categories);
    }
    /*
    根据分类查询分类信息
     */
    @GetMapping(value = "/{id}")
    public RespResult<Category> one(@PathVariable(value = "id") String id){
        Category category=categoryService.getById(id);
        return RespResult.ok(category);
    }
}
