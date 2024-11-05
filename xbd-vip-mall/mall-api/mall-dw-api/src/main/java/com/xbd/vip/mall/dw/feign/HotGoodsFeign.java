package com.xbd.vip.mall.dw.feign;

import com.xbd.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "mall-dw")
@RequestMapping(value = "/hot/goods")
public interface HotGoodsFeign {

    @PostMapping(value = "/search/{size}/{hour}/{max}")
    RespResult<List<Map<String,String>>> searchHotGoods(@PathVariable("size")Integer size,
                                                               @PathVariable("hour")Integer hour,
                                                               @PathVariable("max")Integer max,
                                                               @RequestBody String[] urls);
}
