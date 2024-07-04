package com.xbd.vip.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xbd.vip.mall.mapper.SkuSearchMapper;
import com.xbd.vip.mall.search.model.SkuEs;
import com.xbd.vip.mall.service.SkuSearchService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

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
        //QueryBuilder->搜索条件构造器
        NativeSearchQueryBuilder queryBuilder = queryBuilder(searchMap);
        //分组搜索调用
        group(queryBuilder, searchMap);
        //skuSearchMapper进行搜素
        AggregatedPage<SkuEs> page = (AggregatedPage) skuSearchMapper.search(queryBuilder.build());
        //获取结果集:集合列表,总记录数
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //分组搜索结果解析
        parseGroup(page.getAggregations(), resultMap);
        //属性数据解析
        attrParse(resultMap);
        List<SkuEs> list = page.getContent();
        resultMap.put("list", list);
        resultMap.put("totalElements", page.getTotalElements());
        return resultMap;
    }

    /*
     * 根据keywords构建搜索条件
     */
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

    /***
     * 分组搜索
     */
    public void group(NativeSearchQueryBuilder builder, Map<String, Object> searchMap) {
        //前端没有传入分类参数的时候查询分类集合作为搜索条件
        if (StringUtils.isEmpty((String) searchMap.get("category"))) {
            //分类分组
            builder.addAggregation(AggregationBuilders
                    .terms("categoryList") //查询的数据对应别名
                    .field("categoryName") //根据categoryName分组
                    .size(100));//分组查询100条
        }
        //前端没有传入品牌参数的时候查询品牌集合作为搜索条件
        if (StringUtils.isEmpty((String) searchMap.get("brand"))) {
            //分类分组
            builder.addAggregation(AggregationBuilders
                    .terms("brandList") //查询的数据对应别名,类似map的key
                    .field("brandName") //根据brandName分组
                    .size(100));//分组查询100条
        }
        //查询属性
        builder.addAggregation(AggregationBuilders
                .terms("attrmaps")
                .field("skuAttribute")
                .size(10000));
    }

    /**
     * 解析属性集合
     */
    public void attrParse(Map<String, Object> groupMap) {
        /*
        1、将所有属性信息传过来，并取出存到groupList中
        2、循环groupList
        3、把所有相同属性名的结果集合收集到一起
        4、收集完成后，替换原来groupMap中的属性数据
        */
        //获取属性分组数据
        Object attrgroup = groupMap.get("attrmaps");
        if (attrgroup != null) {
            List<String> groupList = (List<String>) attrgroup;
            Map<String, Set<String>> allMaps = new HashMap<String, Set<String>>();
            for (String attr : groupList) {
                //将当前attr解析成Map
                try {
                    Map<String, String> attrMap = JSON.parseObject(attr, Map.class);
                    for (Map.Entry<String, String> entry : attrMap.entrySet()) {
                        Set<String> values = allMaps.get(entry.getKey());
                        if (values == null) {
                            values = new HashSet<String>();
                        }
                        values.add(entry.getValue());
                        allMaps.put(entry.getKey(),values);
                    }
                }catch (JSONException e){
                    //JSON格式化失败则放弃
                    e.printStackTrace();
                    continue;
                }
            }
            groupMap.put("attrmaps",allMaps);
        }
    }

    /****
     * 取出所有分组数据
     * @param aggregations
     * @return
     */
    public void parseGroup(Aggregations aggregations, Map<String, Object> resultMap) {
        //判空,对应传入了分类和品牌时不需要查询
        if (aggregations != null) {
            //所有分组数据
            for (Aggregation aggregation : aggregations) {
                //强转成ParsedStringTerms
                ParsedStringTerms pst = (ParsedStringTerms) aggregation;
                //定义一个集合存储
                List<String> values = new ArrayList<String>();
                for (Terms.Bucket bucket : pst.getBuckets()) {
                    //单个对象值
                    values.add(bucket.getKeyAsString());
                }
                //存入到Map中
                resultMap.put(pst.getName(), values);
            }
        }
    }
}
