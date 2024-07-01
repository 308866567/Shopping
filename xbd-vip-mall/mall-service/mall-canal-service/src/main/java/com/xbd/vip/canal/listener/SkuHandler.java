package com.xbd.vip.canal.listener;

import com.alibaba.fastjson.JSON;
import com.xbd.vip.mall.goods.feign.SkuFeign;
import com.xbd.vip.mall.goods.model.Sku;
import com.xbd.vip.mall.search.feign.SkuSearchFeign;
import com.xbd.vip.mall.search.model.SkuEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable(value = "sku")
@Component
public class SkuHandler implements EntryHandler<Sku> {
    @Autowired
    private SkuSearchFeign skuSearchFeign;

    /*
        增加数据监听
     */
    @Override
    public void insert(Sku sku) {
        if (sku.getStatus().intValue() == 1) {
            //将sku转成json,再转成skuEs
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(sku), SkuEs.class));
        }

    }

    @Override
    public void update(Sku before, Sku after) {
        if (after.getStatus().intValue() == 2) {
            skuSearchFeign.del(after.getId());
        } else if (after.getStatus().intValue() == 1) {
            //将sku转成json,再转成skuEs
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SkuEs.class));
        }
    }

    @Override
    public void delete(Sku sku) {
        skuSearchFeign.del(sku.getId());
    }
}
