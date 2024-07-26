package com.xbd.vip.mall.seckill.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbd.vip.mall.seckill.mapper.SeckillGoodsMapper;
import com.xbd.vip.mall.seckill.mapper.SeckillOrderMapper;
import com.xbd.vip.mall.seckill.model.SeckillGoods;
import com.xbd.vip.mall.seckill.model.SeckillOrder;
import com.xbd.vip.mall.seckill.service.SeckillGoodsService;
import com.xbd.vip.mall.seckill.service.SeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper,SeckillOrder> implements SeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;


}
