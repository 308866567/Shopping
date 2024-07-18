package com.xbd.vip.mall.cart.controller;

import com.google.common.collect.Lists;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.cart.model.Cart;
import com.xbd.vip.mall.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    /*
        根据购物车商品id集合查询购物车列表
     */
    @PostMapping(value = "list")
    public RespResult<List<Cart>> list(@RequestBody List<String> ids) {
        //购物车集合
        List<Cart>  carts = Lists.newArrayList(cartService.list(ids));
        System.out.println(carts);
        return RespResult.ok(carts);
    }
    /****
     * 根据用户名查询购物车列表
     * http://localhost:8087/cart/list
     * @return
     */
    @GetMapping(value = "/list")
    @CrossOrigin
    public RespResult<List<Cart>> list(){
        String userName="testUser";//TODO 用户名传入,当前为固定
        List<Cart> carts = cartService.list(userName);
        return RespResult.ok(carts);
    }
    /*
    增加购物车方法
    localhost:8087/cart/1319051488240168961/2
    localhost:8087/cart/1318599511605563394/1
     */
    @GetMapping(value = "/{id}/{num}")
    public RespResult add(@PathVariable(value = "id") String id,
                          @PathVariable(value = "num") Integer num
    ) {
        String userName = "testUser";//TODO 用户名传入,当前为固定
        cartService.add(id, userName, num);
        return RespResult.ok();
    }

}
