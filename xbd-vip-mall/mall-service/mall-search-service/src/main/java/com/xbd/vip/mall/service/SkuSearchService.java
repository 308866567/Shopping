package com.xbd.vip.mall.service;

import com.xbd.vip.mall.search.model.SkuEs;

import java.util.Map;

public interface SkuSearchService {
    //添加索引
    void add(SkuEs skuEs);
    //删除索引
    void del(String id);
    /***
     * 商品搜索
     * @param searchMap
     * @return
     */
    Map<String ,Object> search(Map<String ,Object> searchMap);

}
