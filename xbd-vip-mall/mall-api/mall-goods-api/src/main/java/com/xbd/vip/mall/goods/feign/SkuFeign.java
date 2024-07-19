package com.xbd.vip.mall.goods.feign;

import com.xbd.vip.mall.cart.model.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.goods.model.Sku;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "mall-goods")
@RequestMapping(value = "/sku")
public interface SkuFeign {

    /**
     * 库存递减
     * @param carts
     * @return
     */
    @PutMapping(value = "/dcount")
     RespResult dcount(@RequestBody List<Cart> carts);

    @GetMapping(value = "/{id}")
    RespResult<Sku> one(@PathVariable(value = "id") String id);
    /****
     * 指定分类下的推广产品列表
     */
    @GetMapping(value = "/aditems/type")
    List<Sku> typeItems(@RequestParam(value = "id") Integer id);

    /****
     * 删除指定分类下的推广产品列表
     */
    @DeleteMapping(value = "/aditems/type")
    RespResult deleteTypeItems(@RequestParam(value = "id") Integer id);

    /****
     * 修改指定分类下的推广产品列表
     */
    @PutMapping(value = "/aditems/type")
    RespResult updateTypeItems(@RequestParam(value = "id") Integer id);
}
