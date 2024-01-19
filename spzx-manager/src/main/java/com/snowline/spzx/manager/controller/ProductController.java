package com.snowline.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.manager.service.ProductService;
import com.snowline.spzx.model.dto.product.ProductDto;
import com.snowline.spzx.model.entity.product.Product;
import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Snow
 * @create 2024-01-19 2:44
 */
@RestController
@RequestMapping(value = "/admin/product/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    //列表 条件分页查询
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable("page") Integer page,
                       @PathVariable("limit") Integer limit,
                       ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findByPage(page, limit, productDto);

        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);

    }


}
