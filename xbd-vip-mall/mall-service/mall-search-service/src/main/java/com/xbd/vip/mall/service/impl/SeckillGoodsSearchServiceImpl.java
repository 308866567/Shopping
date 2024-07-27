package com.xbd.vip.mall.service.impl;

import com.xbd.vip.mall.mapper.SeckillGoodsSearchMapper;
import com.xbd.vip.mall.search.model.SeckillGoodsEs;
import com.xbd.vip.mall.service.SeckillGoodsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillGoodsSearchServiceImpl implements SeckillGoodsSearchService {
    @Autowired
    private SeckillGoodsSearchMapper seckillGoodsSearchMapper;

    /**
     * 秒杀商品导入索引库
     * @param seckillGoodsEs
     */
    @Override
    public void add(SeckillGoodsEs seckillGoodsEs) {
        seckillGoodsSearchMapper.save(seckillGoodsEs);
    }
}
