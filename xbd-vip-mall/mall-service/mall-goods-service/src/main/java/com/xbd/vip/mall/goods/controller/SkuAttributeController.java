package com.xbd.vip.mall.goods.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.goods.model.SkuAttribute;
import com.xbd.vip.mall.goods.service.SkuAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/skuAttribute")
public class SkuAttributeController  {

    @Autowired
    SkuAttributeService skuAttributeService;
    /***
     * 根据分类ID查询
     */
    @GetMapping(value = "/category/{id}")
    public RespResult<List<SkuAttributeController>> categorySkuAttributeList(@PathVariable(value = "id") Integer id){
        //根据分类ID查询属性参数
        List<SkuAttribute> skuAttributes = skuAttributeService.queryList(id);
        return RespResult.ok(skuAttributes);
    }
}
