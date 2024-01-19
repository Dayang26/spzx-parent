package com.snowline.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snowline.spzx.manager.mapper.ProductSpecMapper;
import com.snowline.spzx.manager.service.ProductSpecService;
import com.snowline.spzx.model.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-19 2:02
 */

@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    @Autowired
    private ProductSpecMapper productSpecMapper;


    //列表 分页查询
    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<ProductSpec> list = productSpecMapper.findByPage();
        return new PageInfo<>(list);
    }

    //添加
    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }

    //    修改
    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.updateById(productSpec);
    }

    //删除
    @Override
    public void deleteById(Long id) {
        productSpecMapper.deleteById(id);
    }


    //查询所有商品规格
    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.findAll();
    }
}
