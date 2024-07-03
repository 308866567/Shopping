package com.xbd.vip.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xbd.vip.mall.mapper.SkuSearchMapper;
import com.xbd.vip.mall.search.model.SkuEs;
import com.xbd.vip.mall.service.SkuSearchService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkuSearchServiceImpl implements SkuSearchService {
    @Autowired
    private SkuSearchMapper skuSearchMapper;

    /***
     * 单个导入ES
     * @param skuEs
     */
    @Override
    public void add(SkuEs skuEs) {
        //属性转换
        String skuAttribute = skuEs.getSkuAttribute();
        if (!StringUtils.isEmpty(skuAttribute)) {
            skuEs.setAttrMap(JSON.parseObject(skuAttribute, Map.class));
        }
        skuSearchMapper.save(skuEs);
    }


    /***
     * 根据ID删除
     * @param id
     */
    @Override
    public void del(String id) {
        skuSearchMapper.deleteById(id);
    }

    /***
     * 商品搜索
     * 关键词搜索:keywords->name
     * @param searchMap
     * @return
     */
    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        //QueryBuilder->构建搜索条件
        NativeSearchQueryBuilder queryBuilder = queryBuilder(searchMap);
        //skuSearchMapper进行搜素
        Page<SkuEs> page = skuSearchMapper.search(queryBuilder.build());
        //获取结果集:集合列表,总记录数
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<SkuEs> list = page.getContent();
        resultMap.put("list",list);
        resultMap.put("totalElements",page.getTotalElements());
        return resultMap;
    }

    public NativeSearchQueryBuilder queryBuilder(Map<String, Object> searchMap) {
        //判断关键词是否为空,不为空,则设置条件
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if (searchMap != null && searchMap.size() > 0) {
            //获取关键词
            Object keywords = searchMap.get("keywords");
            if (!StringUtils.isEmpty(keywords.toString())) {
                builder.withQuery(QueryBuilders.termQuery("name", keywords.toString()));
            }
        }
        return builder;
    }
}
