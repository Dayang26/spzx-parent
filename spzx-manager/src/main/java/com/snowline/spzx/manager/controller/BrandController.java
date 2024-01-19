package com.snowline.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.snowline.spzx.manager.service.BrandService;
import com.snowline.spzx.model.entity.product.Brand;
import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 19:43
 */


@RestController
@RequestMapping("/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    //列表
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page,
                       @PathVariable Integer limit) {

        PageInfo<Brand> pageInfo = brandService.findByPage(page, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);

    }


    //添加
    @PostMapping("/save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //  TODO 修改


    //  TODO 删除
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        System.out.println("123123 hot-fix");
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    //查询所有品牌
    @GetMapping("/findAll")
    public Result findAll() {

        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }


}
