package com.snowline.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.manager.service.CategoryBrandService;
import com.snowline.spzx.model.dto.product.CategoryBrandDto;
import com.snowline.spzx.model.entity.product.Brand;
import com.snowline.spzx.model.entity.product.CategoryBrand;
import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 21:55
 */

@RestController

@RequestMapping("/admin/product/categoryBrand")
public class CategoryBrandController {
    @Autowired
    private CategoryBrandService categoryBrandService;

    //分类品牌条件分页查询
    @GetMapping("/{page}/{limit}/")
    public Result findByPage(
            @PathVariable Integer page,
            @PathVariable Integer limit,
            CategoryBrandDto categoryBrandDto) {

        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page, limit, categoryBrandDto);

        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //添加
    @PostMapping("/save")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    // TODO 修改


    // TODO 删除


    //根据分类id查询对应品牌数据
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<Brand> list = categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }


}
