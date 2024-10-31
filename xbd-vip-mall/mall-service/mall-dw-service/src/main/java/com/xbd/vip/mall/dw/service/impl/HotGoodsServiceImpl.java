package com.xbd.vip.mall.dw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.dw.model.HotGoods;
import com.xbd.vip.mall.dw.mapper.HotGoodsMapper;
import com.xbd.vip.mall.dw.service.HotGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotGoodsServiceImpl extends ServiceImpl<HotGoodsMapper, HotGoods> implements HotGoodsService {

    @Autowired
    private HotGoodsMapper hotGoodsMapper;
    @Override
    public List<HotGoods> topNum(Integer size) {
        return hotGoodsMapper.topNum(size);

    }
}
