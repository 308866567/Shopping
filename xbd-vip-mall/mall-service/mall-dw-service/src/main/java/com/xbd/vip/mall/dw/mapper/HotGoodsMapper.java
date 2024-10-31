package com.xbd.vip.mall.dw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xbd.vip.mall.dw.model.HotGoods;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface HotGoodsMapper extends BaseMapper<HotGoods> {

    @Select("SELECT __time as accesstime,  ip, uri FROM mslogs limit #{size}")
    List<HotGoods> topNum(Integer size);
}
