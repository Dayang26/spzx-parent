package com.snowline.spzx.manager.service;

import com.snowline.spzx.model.entity.base.ProductUnit;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-20 0:47
 */
public interface ProductUnitService {


    //查询所有单位
    List<ProductUnit> findAll();
}
