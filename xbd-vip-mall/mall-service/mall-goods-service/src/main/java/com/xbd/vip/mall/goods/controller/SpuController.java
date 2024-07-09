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

    /*
    产品保存
     */
    @PostMapping(value = "/save")
    public RespResult save(@RequestBody Product product) {
        spuService.saveProduct(product);
        return RespResult.ok();
    }

    /*
    根据spu-id查询product
     */
    @GetMapping(value = "/product/{id}")
    public RespResult<Product> one(@PathVariable(value = "id") String id) {
        //查询spu-id
        //查询sku集合
        Product product = spuService.findBySpuId(id);
        return RespResult.ok(product);
    }
}
