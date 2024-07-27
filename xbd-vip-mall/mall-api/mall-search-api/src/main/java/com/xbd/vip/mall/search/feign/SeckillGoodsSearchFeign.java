package com.xbd.vip.mall.search.feign;


import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.search.model.SeckillGoodsEs;
import com.xbd.vip.mall.search.model.SkuEs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//feign接口所对应的服务的名字,对应yaml文件的服务名字
@FeignClient(value = "mall-search")
@RequestMapping(value = "/seckill/goods")
public interface SeckillGoodsSearchFeign {
    /**
     * 秒杀商品导入索引库
     * @param seckillGoodsEs
     * @return
     */
    @PostMapping(value = "/import")
    RespResult add(@RequestBody SeckillGoodsEs seckillGoodsEs);
}
