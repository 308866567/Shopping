package com.xbd.vip.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xbd.vip.mall.goods.model.Product;
import com.xbd.vip.mall.goods.model.Spu;

public interface SpuService extends IService<Spu> {

    void saveProduct(Product product);
}
