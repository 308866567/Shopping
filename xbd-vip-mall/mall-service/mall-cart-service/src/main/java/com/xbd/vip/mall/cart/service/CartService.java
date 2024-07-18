package com.xbd.vip.mall.cart.service;

import com.xbd.vip.mall.cart.model.Cart;

import java.util.List;

public interface CartService {
    /**
     * 根据集合id删除指定的购物车商品
     * @param ids
     */
    void delete(List<String> ids);
    /**
     * 加入购物车
     * @param id
     * @param userName
     * @param num
     */
    void add(String id,String userName,Integer num);
    /**
     * 根据用户名查询购物车列表
     * @param userName
     * @return
     */
    List<Cart> list(String userName);
    /**
     * @param ids
     * @return
     */
    Iterable<Cart> list(List<String> ids);
}
