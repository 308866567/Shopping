package com.xbd.vip.canal.listener;

import com.alibaba.fastjson.JSON;
import com.xbd.vip.canal.job.dynamic.DynamicJob;
import com.xbd.vip.canal.job.dynamic.DynamicTaskCreate;
import com.xbd.vip.mall.page.feign.SeckillPageFeign;
import com.xbd.vip.mall.search.feign.SeckillGoodsSearchFeign;
import com.xbd.vip.mall.search.model.SeckillGoodsEs;
import com.xbd.vip.mall.seckill.model.SeckillActivity;
import com.xbd.vip.mall.seckill.model.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

import java.text.SimpleDateFormat;

@Component
@CanalTable(value = "seckill_activity")
public class SeckillActivityHandler implements EntryHandler<SeckillActivity> {

    @Autowired
    private DynamicTaskCreate dynamicTaskCreate;
    /**
     * 活动添加时,创建删除活动的任务
     * @param seckillActivity
     */
    @Override
    public void insert(SeckillActivity seckillActivity) {
        //常用时间倒过即可
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String cron=simpleDateFormat.format(seckillActivity.getEndTime());
        //传递的额外参数(活动id)用于根据当前活动id查询有哪些商品在该活动下,然后更新商品对应静态页和索引
        dynamicTaskCreate.create(seckillActivity.getId(),cron,1,new DynamicJob(),seckillActivity.getId());
    }

    @Override
    public void update(SeckillActivity before, SeckillActivity after) {
        //常用时间倒过即可
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String cron=simpleDateFormat.format(after.getEndTime());
        //传递的额外参数(活动id)用于根据当前活动id查询有哪些商品在该活动下,然后更新商品对应静态页和索引
        dynamicTaskCreate.create(after.getId(),cron,1,new DynamicJob(),after.getId());
    }

    @Override
    public void delete(SeckillActivity seckillActivity) {
        EntryHandler.super.delete(seckillActivity);
    }
}
