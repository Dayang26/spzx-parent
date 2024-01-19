package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Snow
 * @create 2024-01-20 1:41
 */

@Mapper
public interface ProductDetailsMapper {


    //3 保存商品详细数据 product_details 表
    void save(ProductDetails productDetails);


}
