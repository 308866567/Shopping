package com.xbd.vip.canal.listener;

import com.xbd.vip.mall.goods.model.AdItems;
import com.xbd.vip.mall.goods.model.Brand;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable(value = "brand")
public class BrandHandler implements EntryHandler<Brand> {

    @Override
    public void insert(Brand brand) {
        System.out.println("品牌插入");
        EntryHandler.super.insert(brand);
    }

    @Override
    public void update(Brand before, Brand after) {
        System.out.println("品牌修改");
        EntryHandler.super.update(before, after);
    }

    @Override
    public void delete(Brand brand) {
        System.out.println("品牌删除");
        EntryHandler.super.delete(brand);
    }
}
