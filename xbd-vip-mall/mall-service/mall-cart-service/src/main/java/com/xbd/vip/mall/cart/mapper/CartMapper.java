package com.xbd.vip.mall.cart.mapper;

import com.xbd.vip.mall.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CartMapper extends MongoRepository<Cart,String> {
}
