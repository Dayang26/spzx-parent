package com.snowline.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 19:43
 */
public interface BrandService {

    //列表
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    //    添加
    void save(Brand brand);


    //查询所有品牌
    List<Brand> findAll();

    //    删除
    void deleteById(Integer id);
}
