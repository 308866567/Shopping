package com.xbd.vip.mall.cart.feign;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.cart.model.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "mall-cart")//服务名
@RequestMapping(value = "/cart")
public interface CartFeign {
    @PostMapping(value = "list")
    RespResult<List<Cart>> list(@RequestBody List<String> ids);
    @DeleteMapping()
    RespResult delete(@RequestBody List<String> ids);
}
