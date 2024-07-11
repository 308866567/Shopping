package com.xbd.vip.canal.listener;

import com.alibaba.fastjson.JSON;
import com.xbd.vip.mall.goods.model.Sku;
import com.xbd.vip.mall.page.feign.PageFeign;
import com.xbd.vip.mall.search.feign.SkuSearchFeign;
import com.xbd.vip.mall.search.model.SkuEs;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable(value = "sku")
public class SkuHandler implements EntryHandler<Sku> {
    @Autowired
    private SkuSearchFeign skuSearchFeign;

    @Autowired
    private PageFeign pageFeign;

    /*
        增加数据监听
     */
    @SneakyThrows
    @Override
    public void insert(Sku sku) {
        if (sku.getStatus().intValue() == 1) {
            //将sku转成json,再转成skuEs
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(sku), SkuEs.class));
        }
        //生成静态页
        pageFeign.html(sku.getSpuId());
    }

    @SneakyThrows
    @Override
    public void update(Sku before, Sku after) {
        if(after.getStatus().intValue()==2){
            //导入索引
            skuSearchFeign.del(after.getId());
        }else{
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SkuEs.class));
        }
        //生成静态页
        pageFeign.html(after.getSpuId());
    }

    @Override
    public void delete(Sku sku) {
        skuSearchFeign.del(sku.getId());
        // TODO 删除静态页
    }
}
