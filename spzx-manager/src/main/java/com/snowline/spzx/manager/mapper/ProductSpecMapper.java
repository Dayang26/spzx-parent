package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-19 2:03
 */

@Mapper
public interface ProductSpecMapper {


    //列表 分页查询
    List<ProductSpec> findByPage();

    //添加
    void save(ProductSpec productSpec);


    //    修改
    void updateById(ProductSpec productSpec);

    //删除
    void deleteById(Long id);

    //查询所有商品规格
    List<ProductSpec> findAll();
}
