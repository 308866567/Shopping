package com.xbd.vip.canal.listener;


import com.xbd.vip.mall.goods.feign.SkuFeign;
import com.xbd.vip.mall.goods.model.AdItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable(value = "ad_items")
public class AdItemsHandler implements EntryHandler<AdItems> {


    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void insert(AdItems adItems) {
//        System.out.println("insert\n");
//        System.out.println("adItems = " + adItems);

        skuFeign.updateTypeItems(adItems.getId());
    }

    @Override
    public void update(AdItems before, AdItems after) {
//        System.out.println("update\n");
//        System.out.println(before);
//        System.out.println(after);

        if (before.getType().intValue() != after.getType().intValue()) {
            skuFeign.updateTypeItems(before.getId());
        }
        skuFeign.updateTypeItems(after.getId());
    }

    @Override
    public void delete(AdItems adItems) {
//        System.out.println("delete\n");
//        System.out.println("adItems = " + adItems);
        skuFeign.deleteTypeItems(adItems.getId());
    }
}
