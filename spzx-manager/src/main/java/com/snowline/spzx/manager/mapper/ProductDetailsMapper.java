package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Snow
 * @create 2024-01-20 1:41
 */

@Mapper
public interface ProductDetailsMapper {


    //保存商品详细数据 product_details 表
    void save(ProductDetails productDetails);

    //根据id查询商品详细信息 product_details
    ProductDetails findProductDetailsByProductId(Long id);

    //    修改product-details
    void updateById(ProductDetails productDetails);


    //根据商品id 删除product-details表
    void deleteByProductId(Long id);
}
