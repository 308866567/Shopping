package com.xbd.vip.mall.page.feign;

import com.xbd.mall.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mall-web-page")
@RequestMapping(value = "/page")
public interface PageFeign {

    /***
     * 生成静态页
     */
    @GetMapping(value = "/{id}")
    RespResult html(@PathVariable(value = "id") String id) throws Exception;
}
