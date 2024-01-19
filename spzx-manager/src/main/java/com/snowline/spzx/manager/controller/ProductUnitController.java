package com.snowline.spzx.manager.controller;

import com.snowline.spzx.manager.service.ProductUnitService;
import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Snow
 * @create 2024-01-20 0:46
 */

@RestController
@RequestMapping(value = "/admin/product/productUnit")
public class ProductUnitController {
    @Autowired
    private ProductUnitService productUnitService;

    //查询所有单位
    @GetMapping("/findAll")
    public Result findAll() {
        return Result.build(
                productUnitService.findAll(),
                ResultCodeEnum.SUCCESS);
    }

}
