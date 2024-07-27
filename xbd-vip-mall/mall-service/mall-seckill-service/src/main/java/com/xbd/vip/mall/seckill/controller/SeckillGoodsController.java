package com.xbd.vip.mall.seckill.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.seckill.model.SeckillGoods;
import com.xbd.vip.mall.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/seckill/goods")
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    /**
     * mysql查询商品详细
     * localhost:8092/seckill/goods/1
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    RespResult<SeckillGoods> one(@PathVariable("id") String id){
        return RespResult.ok(seckillGoodsService.getById(id));
    }


}
