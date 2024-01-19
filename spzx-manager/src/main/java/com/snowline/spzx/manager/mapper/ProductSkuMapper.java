package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-20 1:40
 */

@Mapper
public interface ProductSkuMapper {

    //获取商品sku列表集合，保存sku信息 produck_sku表
    void save(ProductSku productSku);


    //根据id查询商品sku信息列表 product_sku
    List<ProductSku> findProductSkuByProductId(Long id);


    //    修改product-sku
    void updateById(ProductSku productSku);


    //根据商品id 删除product—sku表
    void deleteByProductId(Long id);
}
