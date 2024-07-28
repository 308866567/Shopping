package com.xbd.vip.canal.listener;

import com.alibaba.fastjson.JSON;
import com.xbd.vip.mall.goods.model.Sku;
import com.xbd.vip.mall.page.feign.PageFeign;
import com.xbd.vip.mall.page.feign.SeckillPageFeign;
import com.xbd.vip.mall.search.feign.SeckillGoodsSearchFeign;
import com.xbd.vip.mall.search.feign.SkuSearchFeign;
import com.xbd.vip.mall.search.model.SeckillGoodsEs;
import com.xbd.vip.mall.search.model.SkuEs;
import com.xbd.vip.mall.seckill.feign.SeckillGoodsFeign;
import com.xbd.vip.mall.seckill.model.SeckillGoods;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable(value = "seckill_goods")
public class SeckillHandler implements EntryHandler<SeckillGoods> {
    @Autowired
    private SeckillGoodsSearchFeign seckillGoodsSearchFeign;
    @Autowired
    private SeckillPageFeign seckillPageFeign;

    /**
     * 增加秒杀商品
     * @param seckillGoods
     */
    @Override
    public void insert(SeckillGoods seckillGoods)    {
        try {
            //ES索引
            SeckillGoodsEs seckillGoodsEs = JSON.parseObject(JSON.toJSONString(seckillGoods), SeckillGoodsEs.class);
            seckillGoodsSearchFeign.add(seckillGoodsEs);
            //静态页
            seckillPageFeign.page(seckillGoods.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 秒杀商品修改
     * @param before
     * @param after
     */
    @Override
    public void update(SeckillGoods before, SeckillGoods after) {
        try {
            //ES索引
            SeckillGoodsEs seckillGoodsEs = JSON.parseObject(JSON.toJSONString(after), SeckillGoodsEs.class);
            seckillGoodsSearchFeign.add(seckillGoodsEs);
            //静态页
            seckillPageFeign.page(after.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(SeckillGoods seckillGoods) {
        EntryHandler.super.delete(seckillGoods);
    }
}
