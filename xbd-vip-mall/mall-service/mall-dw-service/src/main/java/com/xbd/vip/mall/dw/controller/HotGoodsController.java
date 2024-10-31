package com.xbd.vip.mall.dw.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.dw.model.HotGoods;
import com.xbd.vip.mall.dw.service.HotGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/hot/goods")
public class HotGoodsController {
    @Autowired
    private HotGoodsService hotGoodsService;

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

    /**
     * localhost:8093/hot/goods/top/2
     * @param size
     * @return
     */
    @GetMapping(value = "/top/{size}")
    public  RespResult<List<HotGoods>> topNum(@PathVariable("size") Integer size) {
        List<HotGoods> list = hotGoodsService.topNum(size);
        return RespResult.ok(list);
    }
}
