package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.dto.product.CategoryBrandDto;
import com.snowline.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 21:57
 */


@Mapper
public interface CategoryBrandMapper {


    //分类品牌条件分页查询

    //    List<CategoryBrand> findByPage(Integer page, Integer limit, Long brandId,Long categoryId);
    List<CategoryBrand> findByPage(@Param("page") Integer page, @Param("limit") Integer limit, @Param("categoryBrandDto") CategoryBrandDto categoryBrandDto);


    //添加
    void save(CategoryBrand categoryBrand);
}
