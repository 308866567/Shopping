package com.xbd.vip.mall.page.service.impl;

import com.alibaba.fastjson.JSON;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.goods.feign.CategoryFeign;
import com.xbd.vip.mall.goods.feign.SkuFeign;
import com.xbd.vip.mall.goods.feign.SpuFeign;
import com.xbd.vip.mall.goods.model.Category;
import com.xbd.vip.mall.goods.model.Product;
import com.xbd.vip.mall.goods.model.Sku;
import com.xbd.vip.mall.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;


@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private CategoryFeign categoryFeign;

    @Autowired
    private SpuFeign spuFeign;

    @Value("${pagepath}")
    private String pagePath;

    @Autowired
    private TemplateEngine templateEngine;

    /*
    生成静态页
     */
    @Override
    public void html(String id) throws Exception {
        //加载产品数据
        Map<String, Object> dataMap = dataLoad(id);

        //1创建上下文对象thymeleaf
        Context context = new Context();
        //2设置模板数据
        context.setVariables(dataMap);
        //3指定文件生成后存储路径
        File dest = new File(pagePath, id + ".html");
        PrintWriter writer = new PrintWriter(dest, "UTF-8");
        //4执行合成生成
        //参数:模板名称,内容,输出
        templateEngine.process("item", context, writer);
    }

    public Map<String, Object> dataLoad(String id) {
        //查询商品数据
        RespResult<Product> productResult = spuFeign.one(id);
        Product product = productResult.getData();
        //处理数据
        if (product != null) {
            //MAP
            Map<String, Object> dataMap = new HashMap<String, Object>();
            //Spu
            dataMap.put("spu", product.getSpu());
            //图片处理,图片以逗号拼接
            dataMap.put("images",  product.getSpu().getImages().split(","));
            //属性列表
            dataMap.put("attrs", JSON.parseObject(product.getSpu().getAttributeList(), Map.class));
            //三级分类
            RespResult<Category> one = categoryFeign.one(product.getSpu().getCategoryOneId().toString());
            RespResult<Category> two = categoryFeign.one(product.getSpu().getCategoryTwoId().toString());
            RespResult<Category> three = categoryFeign.one(product.getSpu().getCategoryThreeId().toString());
            dataMap.put("one", one.getData());
            dataMap.put("two", two.getData());
            dataMap.put("three", three.getData());
            //点击切换sku
            //Sku集合转JSON
            List<Sku> skus = product.getSkus();
            List<Map<String, Object>> skuList = new ArrayList<Map<String, Object>>();
            for (Sku sku : skus) {
                Map<String, Object> skuMap = new HashMap<String, Object>();
                skuMap.put("id", sku.getId());
                skuMap.put("name", sku.getName());
                skuMap.put("price", sku.getPrice());
                skuMap.put("attr", sku.getSkuAttribute());
                //添加到集合中
                skuList.add(skuMap);
            }
            dataMap.put("skuList", skuList);
            return dataMap;
        }
        return null;
    }
}
