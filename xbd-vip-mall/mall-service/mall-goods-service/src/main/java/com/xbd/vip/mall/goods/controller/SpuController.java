package com.xbd.vip.mall.goods.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.goods.model.Product;
import com.xbd.vip.mall.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/spu")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;

    @PostMapping(value = "/save")
    public RespResult save(@RequestBody Product product){
        spuService.saveProduct(product);
        return  RespResult.ok();
    }
}
