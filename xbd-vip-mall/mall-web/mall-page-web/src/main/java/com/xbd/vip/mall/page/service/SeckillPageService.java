package com.xbd.vip.mall.page.service;

public interface SeckillPageService {
    //秒杀商品详情页生成
    void html(String id) throws Exception;

    //删除秒杀详情页
    void delete(String id);
}
