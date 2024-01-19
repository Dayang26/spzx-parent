package com.snowline.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.model.dto.product.ProductDto;
import com.snowline.spzx.model.entity.product.Product;

/**
 * @author Snow
 * @create 2024-01-19 2:45
 */
public interface ProductService {
    //列表 条件分页查询
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

}
