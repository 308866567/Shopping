package com.xbd.vip.mall.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xbd.mall.util.RespResult;
import com.xbd.vip.mall.goods.model.Brand;
import com.xbd.vip.mall.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    /****
     * 增加方法 http://127.0.0.1:8081/brand
     */
    @PostMapping
    public RespResult add(@RequestBody Brand brand){
        brandService.save(brand);
        return RespResult.ok();
    }

    /****
     * 修改方法
     */
    @PutMapping
    public RespResult update(@RequestBody Brand brand){
        brandService.updateById(brand);
        return RespResult.ok();
    }

    /****
     * 删除方法
     */
    @DeleteMapping("/{id}")
    public RespResult delete(@PathVariable(value = "id")String id){
        brandService.removeById(id);
        return RespResult.ok();
    }

    /****
     * 条件查询
     */
    @PostMapping(value = "/search")
    public RespResult<List<Brand>> queryList(@RequestBody Brand brand){
        List<Brand> brands = brandService.queryList(brand);
        System.out.println("brands = " + brands);
        return RespResult.ok(brands);
    }

    /****
     * 条件查询
     */
    @PostMapping(value = "/search/{page}/{size}")
    public RespResult<Page<Brand>> queryPageList(
            @PathVariable(value = "page")Long page,
            @PathVariable(value = "size")Long size,
            @RequestBody Brand brand){
        Page<Brand> pageInfo = brandService.queryPageList(brand,page,size);
        System.out.println("pageInfo = " + pageInfo);
        return RespResult.ok(pageInfo);
    }

    /****
     * 根据分类ID查询品牌
     */
    @GetMapping(value = "/category/{id}")
    public RespResult<List<Brand>> categoryBrands(@PathVariable(value = "id") Integer id){
        List<Brand> brands = brandService.queryByCategoryId(id);
        return RespResult.ok(brands);
    }
}
