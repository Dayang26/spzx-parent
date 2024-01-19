package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.dto.product.ProductDto;
import com.snowline.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-19 2:46
 */


@Mapper
public interface ProductMapper {
    //列表 条件分页查询
    List<Product> findByPage(ProductDto productDto);


    //保存商品基本信息 product表
    void save(Product product);

    //根据id查询商品基本信息 product
    Product findProductById(Long id);


//    修改product
    void updateById(Product product);
}
