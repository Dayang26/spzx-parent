package com.snowline.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.model.entity.product.ProductSpec;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-19 2:02
 */
public interface ProductSpecService {
    //列表 分页查询
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    //添加
    void save(ProductSpec productSpec);

    //修改
    void updateById(ProductSpec productSpec);

    //删除
    void deleteById(Long id);

    //查询所有商品规格
    List<ProductSpec> findAll();
}
