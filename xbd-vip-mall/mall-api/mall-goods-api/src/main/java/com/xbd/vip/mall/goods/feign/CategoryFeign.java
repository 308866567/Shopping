package com.xbd.vip.mall.goods.feign;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.goods.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value ="mall-goods" )
@RequestMapping(value = "/category")
public interface CategoryFeign {
    @GetMapping(value = "/{id}")
    RespResult<Category> one(@PathVariable(value = "id") String id);
}
