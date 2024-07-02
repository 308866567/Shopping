package com.xbd.vip.mall.goods.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    // Spu
    private Spu spu;
    // Sku
    private List<Sku> skus;
}
