package com.xbd.vip.mall.goods.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xbd.vip.mall.goods.model.Sku;
import com.xbd.vip.mall.goods.model.SkuAttribute;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SkuAttributeMapper extends BaseMapper<SkuAttribute> {
    /**
     * 1.根据分类id查询属性id集合
     * 2.根据属性id集合查询属性集合
     */
    @Select("SELECT * FROM sku_attribute WHERE id IN(SELECT attr_id FROM category_attr WHERE category_id=#{id})")
    List<SkuAttribute> queryByCategory(Integer id);

}
