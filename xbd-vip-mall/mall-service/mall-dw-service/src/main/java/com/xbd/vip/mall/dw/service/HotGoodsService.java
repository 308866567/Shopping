package com.xbd.vip.mall.dw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.dw.model.HotGoods;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HotGoodsService extends IService<HotGoods> {
   List<HotGoods> topNum(Integer size);
}
