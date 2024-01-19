package com.snowline.spzx.manager.service.impl;

import com.snowline.spzx.manager.mapper.ProductUnitMapper;
import com.snowline.spzx.manager.service.ProductUnitService;
import com.snowline.spzx.model.entity.base.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-20 0:47
 */

@Service
public class ProductUnitServiceImpl implements ProductUnitService {


    @Autowired
    private ProductUnitMapper productUnitMapper;


    //查询所有单位
    @Override
    public List<ProductUnit> findAll() {
        return  productUnitMapper.findAll();
    }
}
