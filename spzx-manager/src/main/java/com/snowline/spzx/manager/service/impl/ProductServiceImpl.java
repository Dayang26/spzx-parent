package com.snowline.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snowline.spzx.manager.mapper.ProductDetailsMapper;
import com.snowline.spzx.manager.mapper.ProductMapper;
import com.snowline.spzx.manager.mapper.ProductSkuMapper;
import com.snowline.spzx.manager.mapper.ProductSpecMapper;
import com.snowline.spzx.manager.service.ProductService;
import com.snowline.spzx.model.dto.product.ProductDto;
import com.snowline.spzx.model.entity.product.Product;
import com.snowline.spzx.model.entity.product.ProductDetails;
import com.snowline.spzx.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-19 2:45
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;


    //列表 条件分页查询
    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page, limit);
        List<Product> list = productMapper.findByPage(productDto);

        return new PageInfo<>(list);
    }


    //添加
    @Override
    public void save(Product product) {
        //设置审核状态 和上架状态
        product.setStatus(0);
        product.setAuditStatus(0);

        //1 保存商品基本信息 product表
        productMapper.save(product);

        int i = 1;
        //2 获取商品sku列表集合，保存sku信息 produck_sku表
        for (ProductSku productSku : product.getProductSkuList()) {
            //设置商品编号
            productSku.setSkuCode(product.getId() + "_" + i++);
            //设置商品id
            productSku.setProductId(product.getId());
            //设置skuName
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            //设置默认销量
            productSku.setSaleNum(0);
            //设置上架状态
            productSku.setStatus(0);

            productSkuMapper.save(productSku);
        }


        //3 保存商品详细数据 product_details 表
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);
    }
}
