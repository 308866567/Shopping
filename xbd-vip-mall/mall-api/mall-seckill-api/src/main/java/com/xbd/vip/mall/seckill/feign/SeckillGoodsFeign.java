package com.xbd.vip.mall.seckill.feign;


import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.seckill.model.SeckillGoods;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "mall-seckill")
@RequestMapping("/seckill/goods")
public interface SeckillGoodsFeign {
    /**
     * 根据id查询秒杀商品详情
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    RespResult<SeckillGoods> one(@PathVariable("id") String id);

    /**
     * 根据活动查询秒杀商品集合
     * @param acid
     * @return
     */
    @GetMapping(value = "/act/{acid}")
    RespResult<List<SeckillGoods>> actGoods(@PathVariable("acid") String acid);
}
