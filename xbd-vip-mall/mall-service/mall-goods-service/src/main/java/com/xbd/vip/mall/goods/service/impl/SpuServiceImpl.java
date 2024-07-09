package com.xbd.vip.mall.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbd.vip.mall.goods.mapper.BrandMapper;
import com.xbd.vip.mall.goods.mapper.CategoryMapper;
import com.xbd.vip.mall.goods.mapper.SkuMapper;
import com.xbd.vip.mall.goods.mapper.SpuMapper;
import com.xbd.vip.mall.goods.model.*;
import com.xbd.vip.mall.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void saveProduct(Product product) {
        //1保存spu
        //2保存list<sku>
        //Spu
        Spu spu = product.getSpu();
        //商品的保存
        //如果ID为空，则增加
        if (StringUtils.isEmpty(spu.getId())) {
            //上架
            spu.setIsMarketable(1);
            //未删除
            spu.setIsDelete(0);
            //状态
            spu.setStatus(1);
            //添加
            spuMapper.insert(spu);
        } else {
            //ID 不为空，则修改
            spuMapper.updateById(spu);
            //删除之前的Sku记录
            skuMapper.delete(new QueryWrapper<Sku>().eq("spu_id", spu.getId()));
        }
        //上架
        spu.setIsMarketable(1);
        //未删除
        spu.setIsDelete(0);
        //状态
        spu.setStatus(1);
        //添加
        spuMapper.insert(spu);
        //查询三级分类
        Category category = categoryMapper.selectById(spu.getCategoryThreeId());
        //查询品牌
        Brand brand = brandMapper.selectById(spu.getBrandId());
        //当前时间
        Date now = new Date();
        //新增Sku集合
        for (Sku sku : product.getSkus()) {
            //设置名字
            String skuName = spu.getName();
            Map<String, String> attrMap = JSON.parseObject(sku.getSkuAttribute(), Map.class);
            for (Map.Entry<String, String> entry : attrMap.entrySet()) {
                skuName += "   " + entry.getValue();
            }
            sku.setName(skuName);
            //设置图片
            sku.setImages(spu.getImages());
            //设置状态
            sku.setStatus(1);
            //设置类目ID
            sku.setCategoryId(spu.getCategoryThreeId());
            //设置类目名称
            sku.setCategoryName(category.getName());
            //设置品牌ID
            sku.setBrandId(brand.getId());
            //设置品牌名称
            sku.setBrandName(brand.getName());
            //设置Spuid
            sku.setSpuId(spu.getId());
            //时间
            sku.setCreateTime(now);
            sku.setUpdateTime(now);
            //增加
            skuMapper.insert(sku);
        }
    }

    /*
        查询商品详细
     */
    @Override
    public Product findBySpuId(String id) {
        //查询spu-id
        Spu spu = spuMapper.selectById(id);
        //查询sku集合
        QueryWrapper<Sku> queryWrapper = new QueryWrapper<Sku>().eq("spu_id", id);
        List<Sku> skus = skuMapper.selectList(queryWrapper);
        Product product = new Product();
        product.setSpu(spu);
        product.setSkus(skus);
        return product;
    }
}
