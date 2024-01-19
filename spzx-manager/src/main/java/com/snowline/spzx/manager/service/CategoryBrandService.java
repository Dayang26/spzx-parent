package com.snowline.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.model.dto.product.CategoryBrandDto;
import com.snowline.spzx.model.entity.product.Brand;
import com.snowline.spzx.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 21:56
 */
public interface CategoryBrandService {

    //分类品牌条件分页查询
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);


//    添加
    void save(CategoryBrand categoryBrand);


//    根据分类id查询对应品牌数据
    List<Brand> findBrandByCategoryId(Long categoryId);
}
