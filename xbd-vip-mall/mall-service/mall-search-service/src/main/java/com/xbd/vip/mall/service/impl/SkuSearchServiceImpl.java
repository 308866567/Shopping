package com.xbd.vip.mall.service.impl;

import com.xbd.vip.mall.mapper.SkuSearchMapper;
import com.xbd.vip.mall.search.model.SkuEs;
import com.xbd.vip.mall.service.SkuSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuSearchServiceImpl implements SkuSearchService {
    @Autowired
    private SkuSearchMapper skuSearchMapper;

    /***
     * 单个导入ES
     * @param skuEs
     */
    @Override
    public void add(SkuEs skuEs) {
        skuSearchMapper.save(skuEs);
    }


    /***
     * 根据ID删除
     * @param id
     */
    @Override
    public void del(String id) {

        skuSearchMapper.deleteById(id);
    }
}
