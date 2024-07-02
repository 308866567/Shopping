package com.xbd.vip.mall.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.search.model.SkuEs;
import com.xbd.vip.mall.service.SkuSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/search")
public class SkuSearchController {
    @Autowired
    private SkuSearchService skuSearchService;
    /****
     * 单个商品导入
     * 增加索引
     */
    @PostMapping(value = "/add")
    public RespResult add(@RequestBody SkuEs skuEs) {
        skuSearchService.add(skuEs);
        return RespResult.ok();
    }

    /***
     * 根据ID删除索引
     * @param id
     * @return
     */
    @DeleteMapping(value = "/del/{id}")
    public RespResult del(@PathVariable("id") String id) {
        skuSearchService.del(id);
        return RespResult.ok();
    }
}
