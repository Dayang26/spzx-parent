package com.snowline.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snowline.spzx.manager.mapper.BrandMapper;
import com.snowline.spzx.manager.service.BrandService;
import com.snowline.spzx.model.entity.product.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 19:44
 */


@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;


    //列表
    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);

        List<Brand> list = brandMapper.findByPage();

        return new PageInfo<>(list);
    }


    //    添加
    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }


    //查询所有品牌
    @Override
    public List<Brand> findAll() {
        List<Brand> list = brandMapper.findAll();

        return list;
    }
}
