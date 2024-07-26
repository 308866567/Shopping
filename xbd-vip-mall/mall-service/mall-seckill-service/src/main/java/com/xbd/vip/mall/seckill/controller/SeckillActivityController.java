package com.xbd.vip.mall.seckill.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.seckill.model.SeckillActivity;
import com.xbd.vip.mall.seckill.service.SeckillActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/activity")
public class SeckillActivityController {

    @Autowired
    private SeckillActivityService seckillActivityService;

    /**
     * 秒杀活动列表
     * 未结束的,升序,限制5个
     * http://localhost:8092/test/lock
     * http://localhost:8092/activity
     * @return
     */
    @GetMapping
    public RespResult list() {
        List<SeckillActivity> seckillActivities = seckillActivityService.validActivity();
        return RespResult.ok(seckillActivities);
    }
}
