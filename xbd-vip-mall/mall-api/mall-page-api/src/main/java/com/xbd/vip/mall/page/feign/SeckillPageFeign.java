package com.xbd.vip.mall.page.feign;

import com.xbd.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mall-web-page")
@RequestMapping(value = "/page")
public interface SeckillPageFeign {

    /**
     * 秒杀商品详情页
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/seckill/goods/{id}")
    RespResult page(@PathVariable("id")String id) throws Exception;
}
