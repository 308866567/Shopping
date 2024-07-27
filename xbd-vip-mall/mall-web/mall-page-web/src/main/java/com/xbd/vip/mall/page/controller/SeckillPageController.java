package com.xbd.vip.mall.page.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.page.service.PageService;
import com.xbd.vip.mall.page.service.SeckillPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/page")
public class SeckillPageController {

    @Autowired
    private SeckillPageService seckillPageService;

    /**
     * 秒杀商品详情页生成
     *  localhost:8086/page/seckill/goods/1
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/seckill/goods/{id}")
    public RespResult page(@PathVariable("id")String id) throws Exception {
        seckillPageService.html(id);
        return RespResult.ok();
    }
}
