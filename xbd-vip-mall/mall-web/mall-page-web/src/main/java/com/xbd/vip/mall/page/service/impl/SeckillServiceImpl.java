package com.xbd.vip.mall.page.service.impl;

import com.alibaba.fastjson.JSON;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.goods.feign.CategoryFeign;
import com.xbd.vip.mall.goods.feign.SpuFeign;
import com.xbd.vip.mall.goods.model.Category;
import com.xbd.vip.mall.goods.model.Product;
import com.xbd.vip.mall.goods.model.Sku;
import com.xbd.vip.mall.page.service.PageService;
import com.xbd.vip.mall.page.service.SeckillPageService;
import com.xbd.vip.mall.seckill.feign.SeckillGoodsFeign;
import com.xbd.vip.mall.seckill.model.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SeckillServiceImpl implements SeckillPageService {
    @Autowired
    private SeckillGoodsFeign seckillGoodsFeign;


    @Value("${seckillpath}")
    private String pagePath;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void html(String id) throws Exception {

        //1创建上下文对象thymeleaf
        Context context = new Context();


        //加载产品数据
        Map<String, Object> dataMap = dataLoad(id);
        //2设置模板数据
        context.setVariables(dataMap);


        //3指定文件生成后存储路径
        File dest = new File(pagePath, id + ".html");
        PrintWriter writer = new PrintWriter(dest, "UTF-8");
        //4执行合成生成
        //参数:模板名称,内容,输出
        templateEngine.process("seckillitem", context, writer);
    }

    public Map<String, Object> dataLoad(String id) {
        //查询数据
        RespResult<SeckillGoods> goodsResp = seckillGoodsFeign.one(id);
        if (goodsResp.getData() != null) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("item",goodsResp.getData());
            dataMap.put("images",goodsResp.getData().getImages().split(","));
            return dataMap;
        }
        return null;
    }

    @Override
    public void delete(String id) {
        File dest = new File(pagePath, id + ".html");
        if(dest.exists()){
            dest.delete();
        }
    }
}
