package com.xbd.vip.mall.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xbd.vip.mall.seckill.model.SeckillGoods;

import java.util.List;

public interface SeckillGoodsService extends IService<SeckillGoods> {


    //根据活动id查询秒杀商品信息
    List<SeckillGoods> actGoods(String acid);
}
