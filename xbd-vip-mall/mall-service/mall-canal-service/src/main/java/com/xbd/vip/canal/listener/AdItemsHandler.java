package com.xbd.vip.canal.listener;


import com.xbd.vip.mall.goods.model.AdItems;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable(value = "ad_items")
public class AdItemsHandler implements EntryHandler<AdItems> {


    @Override
    public void insert(AdItems adItems) {
//        System.out.println("insert\n");
//        System.out.println("adItems = " + adItems);
    }

    @Override
    public void update(AdItems before, AdItems after) {
//        System.out.println("update\n");
//        System.out.println(before);
//        System.out.println(after);
    }

    @Override
    public void delete(AdItems adItems) {
//        System.out.println("delete\n");
//        System.out.println("adItems = " + adItems);
    }
}
