package com.snowline.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.model.dto.product.CategoryBrandDto;
import com.snowline.spzx.model.entity.product.CategoryBrand;

/**
 * @author Snow
 * @create 2024-01-18 21:56
 */
public interface CategoryBrandService {

    //分类品牌条件分页查询
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);


//    添加
    void save(CategoryBrand categoryBrand);
}
