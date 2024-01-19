package com.snowline.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snowline.spzx.manager.mapper.CategoryBrandMapper;
import com.snowline.spzx.manager.service.CategoryBrandService;
import com.snowline.spzx.model.dto.product.CategoryBrandDto;
import com.snowline.spzx.model.entity.product.Brand;
import com.snowline.spzx.model.entity.product.CategoryBrand;
import com.snowline.spzx.model.entity.system.SysRole;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 21:56
 */

@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;


    //分类品牌条件分页查询
    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto) {


        //设置pageHelper参数
        PageHelper.startPage(page, limit);

//        CategoryBrand categoryBrand = new CategoryBrand();
//        BeanUtils.copyProperties(categoryBrandDto, categoryBrand);
//        List<CategoryBrand> list = categoryBrandMapper.findByPage(page, limit, categoryBrandDto.getBrandId(),categoryBrandDto.getCategoryId());

        List<CategoryBrand> list = categoryBrandMapper.findByPage(page, limit, categoryBrandDto);

        PageInfo<CategoryBrand> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }


    //    添加
    @Override
    public void save(CategoryBrand categoryBrand) {
        categoryBrandMapper.save(categoryBrand);
    }


    //根据分类id查询对应品牌数据
    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        return categoryBrandMapper.findBrandByCategoryId(categoryId);;
    }
}
