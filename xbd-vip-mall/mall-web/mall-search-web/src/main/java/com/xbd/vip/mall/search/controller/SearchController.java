package com.xbd.vip.mall.search.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.mall.util.UrlUtils;
import com.xbd.vip.mall.search.feign.SkuSearchFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.tags.UrlTag;

import java.util.Map;

@Controller
@RequestMapping(value = "/web/search")
public class SearchController {


    @Autowired
    private SkuSearchFeign skuSearchFeign;

    /****
     * 搜索页面跳转
     * 后端用Model给前端传参数
     * @return
     */
    @GetMapping
    public String search(Model model, @RequestParam(required = false) Map<String, Object> searchMap) {
        //搜索
        RespResult<Map<String, Object>> resultMap = skuSearchFeign.search(searchMap);
        model.addAttribute("result",resultMap.getData());
        model.addAttribute("searchMap",searchMap);
        //组装用户访问的url,基础的url是/web/search
        model.addAttribute("url", UrlUtils.map2url("/web/search",searchMap,"page"));
        //没有排序参数的url
        model.addAttribute("urlsort", UrlUtils.replateUrlParameter(
                model.getAttribute("url").toString(),"sfield","sm"));
        return "search";
    }
}
