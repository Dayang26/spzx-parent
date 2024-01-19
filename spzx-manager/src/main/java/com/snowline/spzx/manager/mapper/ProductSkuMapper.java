package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Snow
 * @create 2024-01-20 1:40
 */

@Mapper
public interface ProductSkuMapper {

    //获取商品sku列表集合，保存sku信息 produck_sku表
    void save(ProductSku productSku);
}
