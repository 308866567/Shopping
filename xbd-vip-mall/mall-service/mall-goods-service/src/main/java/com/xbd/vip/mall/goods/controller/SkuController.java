package com.xbd.vip.mall.goods.controller;


import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.goods.model.Sku;
import com.xbd.vip.mall.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping(value = "/aditems/type/{id}")
    public RespResult<List<Sku>> typeItems(@PathVariable(value = "id") Integer id) {
        List<Sku> skus = skuService.typeSkuItems(id);
        return RespResult.ok(skus);
    }
}
