package com.xbd.vip.mall.cart.service.impl;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.cart.mapper.CartMapper;
import com.xbd.vip.mall.cart.model.Cart;
import com.xbd.vip.mall.cart.service.CartService;
import com.xbd.vip.mall.goods.feign.SkuFeign;
import com.xbd.vip.mall.goods.model.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private SkuFeign skuFeign;

    /*
    加入购物车
    num是当前商品总数量
     */
    @Override
    public void add(String id, String userName, Integer num) {
        //id不能冲突
        //1删除id对应的当前商品记录
        cartMapper.deleteById(userName+id);
        if(num>0){
            //2根据id查询sku详细
            RespResult<Sku> skuResp = skuFeign.one(id);
            //3增加
            Cart cart=new Cart();
            cart.setUserName(userName);
            cart.setSkuId(id);
            cart.setNum(num);
            sku2cart(skuResp.getData(),cart);
            //批量增加
            cartMapper.save(cart);
        }
    }

    /***
     * Sku转Cart
     * @param sku
     * @param cart
     */
    public void sku2cart(Sku sku,Cart cart){
        cart.setImage(sku.getImage());
        cart.set_id(cart.getUserName()+cart.getSkuId());
        cart.setName(sku.getName());
        cart.setPrice(sku.getPrice());
        cart.setSkuId(sku.getId());
    }
}
