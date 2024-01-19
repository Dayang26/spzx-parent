package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 19:44
 */

@Mapper
public interface BrandMapper {
    //    列表
    List<Brand> findByPage();


    //    添加
    void save(Brand brand);



    //查询所有品牌
    List<Brand> findAll();
}
