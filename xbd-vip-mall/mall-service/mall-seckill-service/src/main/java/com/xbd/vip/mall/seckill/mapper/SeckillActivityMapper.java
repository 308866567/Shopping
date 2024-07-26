package com.xbd.vip.mall.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xbd.vip.mall.seckill.model.SeckillActivity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SeckillActivityMapper extends BaseMapper<SeckillActivity> {

    /**
     * 秒杀活动列表
     * 未结束的,升序,限制5个
     * @return
     */
    @Select("SELECT * FROM seckill_activity WHERE end_time>NOW() ORDER BY start_time ASC LIMIT 5")
    List<SeckillActivity> validActivity();

}
