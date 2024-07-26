package com.xbd.vip.mall.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xbd.vip.mall.seckill.model.SeckillActivity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SeckillActivityService extends IService<SeckillActivity>{

    /**
     * 秒杀活动列表
     * 未结束的,升序,限制5个
     * @return
     */
    List<SeckillActivity> validActivity();
}
