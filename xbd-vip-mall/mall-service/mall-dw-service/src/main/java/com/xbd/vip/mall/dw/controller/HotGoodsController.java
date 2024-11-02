package com.xbd.vip.mall.dw.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.dw.model.HotGoods;
import com.xbd.vip.mall.dw.service.HotGoodsService;
import com.xbd.vip.mall.dw.util.DruidPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/hot/goods")
public class HotGoodsController {
    @Autowired
    private HotGoodsService hotGoodsService;


    /***
     * 热门商品分析
     * localhost:8093/hot/goods/search/100/1100/1
     */
    @PostMapping(value = "/search/{size}/{hour}/{max}")
    public RespResult<List<Map<String,String>>> searchHotGoods(@PathVariable("size")Integer size,
                                                               @PathVariable("hour")Integer hour,
                                                               @PathVariable("max")Integer max,
                                                               @RequestBody String[] urls){
        List<Map<String,String>> hotGoods = hotGoodsService.searchHotGoods(size, hour,urls,max);
        return RespResult.ok(hotGoods);
    }

    /***
     * 时间查询
     * 带排除商品的
     * localhost:8093/hot/goods/search/100/1100
     * body : ["/msitems/1.html"]
     */
    @PostMapping(value = "/search/{size}/{hour}")
    public RespResult<List<HotGoods>> search(@PathVariable("size")Integer size,
                                             @PathVariable("hour")Integer hour,
                                             @RequestBody String[] urls){
        List<HotGoods> hotGoods = hotGoodsService.search(size, hour,urls);
        return RespResult.ok(hotGoods);
    }

    /***
     * 时间查询,hour小时前
     * localhost:8093/hot/goods/search/100/1100
     */
    @GetMapping(value = "/search/{size}/{hour}")
    public RespResult<List<HotGoods>> search(@PathVariable("size")Integer size,
                                             @PathVariable("hour")Integer hour){
        List<HotGoods> hotGoods = hotGoodsService.search(size, hour);
        return RespResult.ok(hotGoods);
    }

    /***
     * 分页查询+排序
     * localhost:8093/hot/goods/page/1/2/accesstime/desc
     */
    @GetMapping(value = "/page/{page}/{size}/{sort}/{sortType}")
    public RespResult<DruidPage<List<HotGoods>>> pageListSort(
            @PathVariable(value = "page")Integer page,
            @PathVariable(value = "size")Integer size,
            @PathVariable(value = "sort")String sort,
            @PathVariable(value = "sortType")String sortType){
        DruidPage<List<HotGoods>> druidPage = hotGoodsService.pageListSort(size, page,sort,sortType);
        return RespResult.ok(druidPage);
    }

    /***
     * 分页查询
     * localhost:8093/hot/goods/page/1/2
     */
    @GetMapping(value = "/page/{page}/{size}")
    public RespResult<DruidPage<List<HotGoods>>> pageList(
            @PathVariable(value = "page")Integer page,
            @PathVariable(value = "size")Integer size){
        DruidPage<List<HotGoods>> druidPage = hotGoodsService.pageList(size, page);
        return RespResult.ok(druidPage);
    }

    /**
     * 查询前n条
     * localhost:8093/hot/goods/top/2
     * @param size
     * @return
     */
    @GetMapping(value = "/top/{size}")
    public  RespResult<List<HotGoods>> topNum(@PathVariable("size") Integer size) {
        List<HotGoods> list = hotGoodsService.topNum(size);
        return RespResult.ok(list);
    }

    /**
     * 查询所有
     * localhost:8093/hot/goods
     *
     * @return
     */
    @GetMapping()
    public RespResult<List<HotGoods>> list() {
        List<HotGoods> list = hotGoodsService.list();
        return RespResult.ok(list);
    }


}
