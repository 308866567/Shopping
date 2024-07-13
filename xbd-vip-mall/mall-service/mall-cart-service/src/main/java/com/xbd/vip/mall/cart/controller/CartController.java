package com.xbd.vip.mall.cart.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /*
    增加购物车方法
    localhost:8087/cart/1319051488240168961/2
     */
    @GetMapping(value = "/{id}/{num}")
    public RespResult add(@PathVariable(value = "id") String id,
                          @PathVariable(value = "num") Integer num
    ) {
        String userName = "testUser";
        cartService.add(id, userName, num);
        return RespResult.ok();
    }

}
