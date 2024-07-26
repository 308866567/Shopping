package com.xbd.vip.mall.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbd.vip.mall.seckill.mapper.SeckillActivityMapper;
import com.xbd.vip.mall.seckill.model.SeckillActivity;
import com.xbd.vip.mall.seckill.service.SeckillActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillActivityServiceImpl extends ServiceImpl<SeckillActivityMapper,SeckillActivity> implements SeckillActivityService {

    @Autowired
    private SeckillActivityMapper seckillActivityMapper;

    @Override
    public List<SeckillActivity> validActivity() {
        return seckillActivityMapper.validActivity();
    }
}
