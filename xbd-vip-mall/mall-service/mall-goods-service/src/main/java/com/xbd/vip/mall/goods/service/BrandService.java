package com.xbd.vip.mall.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xbd.vip.mall.goods.model.Brand;

import java.util.List;


public interface BrandService extends IService<Brand> {

    /****
     * 条件查询
     * return List<Brand>
     */
    List<Brand> queryList(Brand brand);

    /****
     * 条件分页查询
     * return Page<Brand>
     */
    Page<Brand> queryPageList(Brand brand,Long currentPage,Long size);

    //根据分类ID查询品牌
    List<Brand> queryByCategoryId(Integer id);

}
