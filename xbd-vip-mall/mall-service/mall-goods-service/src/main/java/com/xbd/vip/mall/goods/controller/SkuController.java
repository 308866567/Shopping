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

    @GetMapping(value = "/aditems/type")
    public RespResult<List<Sku>> typeItems(@RequestParam(value = "id") Integer id) {
        List<Sku> skus = skuService.typeSkuItems(id);
        return RespResult.ok(skus);
    }
    /****
     * 删除指定分类下的推广产品列表
     */
    @DeleteMapping(value = "/aditems/type")
    public RespResult deleteTypeItems(@RequestParam(value = "id") Integer id){
        //清理缓存
        skuService.delTypeSkuItems(id);
        return RespResult.ok();
    }

    @PutMapping (value = "/aditems/type")
    public RespResult updateTypeItems(@RequestParam(value = "id") Integer id){
        //清理缓存
        skuService.updateTypeSkuItems(id);
        return RespResult.ok();
    }
}
