package com.xbd.vip.mall.goods.feign;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.goods.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mall-goods")
@RequestMapping(value = "/spu")
public interface SpuFeign {

    /*
   根据spu-id查询product
    */
    @GetMapping(value = "/product/{id}")
    RespResult<Product> one(@PathVariable(value = "id") String id) ;
}
