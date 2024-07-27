package com.xbd.vip.mall.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.search.model.SeckillGoodsEs;
import com.xbd.vip.mall.service.SeckillGoodsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/seckill/goods")
public class SeckillGoodsSearchController {
    @Autowired
    private SeckillGoodsSearchService seckillGoodsSearchService;

    /**
     * 秒杀商品导入索引库
     * localhost:8084/seckill/goods/import
     * @param seckillGoodsEs
     * @return
     */
    @PostMapping(value = "/import")

    public RespResult add(@RequestBody SeckillGoodsEs seckillGoodsEs){
        seckillGoodsSearchService.add(seckillGoodsEs);
        return RespResult.ok();
    }
}
