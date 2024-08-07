package com.xbd.vip.mall.seckill.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.seckill.model.SeckillGoods;
import com.xbd.vip.mall.seckill.model.SeckillOrder;
import com.xbd.vip.mall.seckill.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/seckill/order")
public class SeckillOrderController {

    @Autowired
    private SeckillOrderService seckillOrderService;


}
