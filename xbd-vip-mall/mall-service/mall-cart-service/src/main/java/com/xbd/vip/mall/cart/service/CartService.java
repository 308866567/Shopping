package com.xbd.vip.mall.cart.service;

import com.xbd.vip.mall.cart.model.Cart;

import java.util.List;

public interface CartService {

    /*
    *   加入购物车
     */
    void add(String id,String userName,Integer num);
    /***
     * 根据用户名查询购物车列表
     * @param userName
     * @return
     */
    List<Cart> list(String userName);

    Iterable<Cart> list(List<String> ids);
}
