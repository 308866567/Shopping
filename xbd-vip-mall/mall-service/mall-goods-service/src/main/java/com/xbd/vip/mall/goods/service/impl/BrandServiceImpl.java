package com.xbd.vip.mall.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbd.vip.mall.goods.mapper.BrandMapper;
import com.xbd.vip.mall.goods.model.Brand;
import com.xbd.vip.mall.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper,Brand> implements BrandService {


    @Autowired
    private BrandMapper brandMapper;

    /****
     * 条件查询
     * return List<Brand>
     */
    @Override
    public List<Brand> queryList(Brand brand) {
        //条件包装对象
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<Brand>();
        //根据name查询品牌
        queryWrapper.like("name",brand.getName());
        //根据initial查询
        queryWrapper.eq("initial",brand.getInitial());
        return brandMapper.selectList(queryWrapper);
    }

    /****
     * 条件分页查询
     * return Page<Brand>
     */
    @Override
    public Page<Brand> queryPageList(Brand brand, Long currentPage, Long size) {
        //条件包装对象
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<Brand>();
        //根据name查询品牌
        queryWrapper.like("name",brand.getName());
        return brandMapper.selectPage(new Page<Brand>(currentPage,size),queryWrapper);
    }

    /***
     * 根据分类ID查询品牌
     * @param id
     * @return
     */
    @Override
    public List<Brand> queryByCategoryId(Integer id) {
        //查询分类ID对应的品牌集合
        List<Integer> brandIds = brandMapper.queryBrandIds(id);
        //根据品牌ID集合查询品牌信息
        List<Brand> brands = brandMapper.selectBatchIds(brandIds);
        return brands;
    }


}
