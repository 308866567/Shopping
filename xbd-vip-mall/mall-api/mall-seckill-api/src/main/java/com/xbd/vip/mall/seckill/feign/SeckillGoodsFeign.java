package com.xbd.vip.mall.seckill.feign;


import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.seckill.model.SeckillGoods;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mall-seckill")
@RequestMapping("/seckill/goods")
public interface SeckillGoodsFeign {
    @GetMapping(value = "/{id}")
    RespResult<SeckillGoods> one(@PathVariable("id") String id);
}
