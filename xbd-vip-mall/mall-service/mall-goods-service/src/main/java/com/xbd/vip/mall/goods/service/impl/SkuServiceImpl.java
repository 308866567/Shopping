package com.xbd.vip.mall.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbd.vip.mall.cart.model.Cart;
import com.xbd.vip.mall.goods.mapper.AdItemsMapper;
import com.xbd.vip.mall.goods.mapper.SkuMapper;
import com.xbd.vip.mall.goods.model.AdItems;
import com.xbd.vip.mall.goods.model.Sku;
import com.xbd.vip.mall.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "ad-items-skus")
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private AdItemsMapper adItemsMapper;

    @Autowired
    private SkuMapper skuMapper;

    /**
     * 库存递减
     *
     * @param carts
     */
    @Transactional
    @Override
    public void dcount(List<Cart> carts) {
        for (Cart cart : carts) {
            int count = skuMapper.decount(cart.getSkuId(), cart.getNum());
            if (count <= 0) {
                throw new RuntimeException("库存不足");
            }
        }
    }

    /***
     * 根据推广产品分类ID查询Sku列表
     * cacheNames = "ad-items-skus":命名空间
     * key ="#id":入参id作为缓存的key，使用的是SpEL表达式
     */
    @Cacheable(key = "#id")
    @Override
    public List<Sku> typeSkuItems(Integer id) {
        //1查询当前分类下的所有的列表信息
        QueryWrapper<AdItems> adItemsQueryWrapper = new QueryWrapper<AdItems>();
        adItemsQueryWrapper.eq("type", id);
        List<AdItems> adItems = adItemsMapper.selectList(adItemsQueryWrapper);
        //2根据sku_id查询产品列表信息
        //用stream流取出adItems中的sku集合
        //获取所有SkuId
        List<String> skuIds = adItems.stream().map(adItem -> adItem.getSkuId()).collect(Collectors.toList());
        //批量查询Sku
        List<Sku> skus = skuMapper.selectBatchIds(skuIds);
        return skus;
    }

    /****
     * 清理缓存
     * @param id
     */
    @CacheEvict(key = "#id")
    @Override
    public void delTypeSkuItems(Integer id) {

    }

    /****
     * 修改缓存
     * @param id
     */
    @CachePut(key = "#id")
    @Override
    public List<Sku> updateTypeSkuItems(Integer id) {
        //1查询当前分类下的所有的列表信息
        QueryWrapper<AdItems> adItemsQueryWrapper = new QueryWrapper<AdItems>();
        adItemsQueryWrapper.eq("type", id);
        List<AdItems> adItems = adItemsMapper.selectList(adItemsQueryWrapper);
        //2根据sku_id查询产品列表信息
        //用stream流取出adItems中的sku集合
        //获取所有SkuId
        List<String> skuIds = adItems.stream().map(adItem -> adItem.getSkuId()).collect(Collectors.toList());
        //批量查询Sku
        List<Sku> skus = skuMapper.selectBatchIds(skuIds);
        return skus;
    }
}
