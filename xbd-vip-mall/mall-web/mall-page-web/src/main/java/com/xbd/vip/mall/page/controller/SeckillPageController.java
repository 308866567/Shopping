package com.xbd.vip.mall.page.controller;

import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.page.service.PageService;
import com.xbd.vip.mall.page.service.SeckillPageService;
import com.xbd.vip.mall.seckill.feign.SeckillGoodsFeign;
import com.xbd.vip.mall.seckill.model.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/page")
public class SeckillPageController {

    @Autowired
    private SeckillPageService seckillPageService;

    /**
     * 秒杀商品详情页生成
     * localhost:8086/page/seckill/goods/1
     *
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/seckill/goods/{id}")
    public RespResult page(@PathVariable("id") String id) throws Exception {
        seckillPageService.html(id);
        return RespResult.ok();
    }

    @Autowired
    private SeckillGoodsFeign seckillGoodsFeign;
    /**
     * 删除静态页,传入活动id
     *
     * @return
     */
    @DeleteMapping(value = "/seckill/goods/{acid}")
    public RespResult deleByAct(@PathVariable("acid") String acid) {
        //1.查询当前活动id对应的商品列表数据
        RespResult<List<SeckillGoods>> listRespResult = seckillGoodsFeign.actGoods(acid);
        List<SeckillGoods> goodsList = listRespResult.getData();
        //2.根据列表数据删除页面
        if(goodsList != null && goodsList.size() > 0) {
            //循环删除页面
            for (SeckillGoods seckillGoods : goodsList) {
                seckillPageService.delete(seckillGoods.getId());
            }
        }
        return RespResult.ok();
    }

}
