package com.xbd.vip.mall.search.feign;


import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.search.model.SkuEs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//feign接口所对应的服务的名字,对应yaml文件的服务名字
@FeignClient(value = "mall-search")
@RequestMapping(value = "/search")
public interface SkuSearchFeign {
    @PostMapping(value = "/add")
    RespResult add(@RequestBody SkuEs skuEs);

    /***
     * 根据ID删除索引
     * @param id
     * @return
     */
    @DeleteMapping(value = "/del/{id}")
    RespResult del(@PathVariable("id") String id);

    @GetMapping
    RespResult<Map<String, Object>> search(@RequestParam Map<String, Object> searchMap);

}
