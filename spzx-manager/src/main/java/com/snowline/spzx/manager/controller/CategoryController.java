package com.snowline.spzx.manager.controller;

import com.snowline.spzx.manager.service.CategoryService;

import com.snowline.spzx.model.entity.product.Category;
import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 3:21
 */


@RestController
@RequestMapping(value = "/admin/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //分类列表，每次查询一层数据
    @GetMapping("/findCategoryList/{id}")
    public Result findCategoryList(@PathVariable("id") Long id) {
        List<Category> list = categoryService.findCategoryList(id);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }



    //文件导出
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse httpServletResponse) {
        categoryService.exportData(httpServletResponse);
    }


    //文件导入
    @PostMapping("/importData")
    public Result importData(MultipartFile file){

        //获取上传文件
        categoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }



}
