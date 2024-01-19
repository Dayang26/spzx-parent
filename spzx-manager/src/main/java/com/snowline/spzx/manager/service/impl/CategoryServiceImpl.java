package com.snowline.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.snowline.spzx.common.exception.SnowLineException;
import com.snowline.spzx.manager.listener.ExcelListener;
import com.snowline.spzx.manager.mapper.CategoryMapper;
import com.snowline.spzx.manager.service.CategoryService;
import com.snowline.spzx.model.entity.product.Category;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import com.snowline.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 3:22
 */

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    //分类列表，每次查询一层数据
    @Override
    public List<Category> findCategoryList(Long id) {
        //根据id值进行查询 返回list集合
        List<Category> categoryList = categoryMapper.selectCategoryByParentId(id);


        //遍历list集合 如果分类有下一个分类 设置hasChildren =true
        if (!CollectionUtils.isEmpty(categoryList)) {
            categoryList.forEach(category -> {
                //判断每一层分类是否有下一层分类
                int count = categoryMapper.selectCountByParentId(category.getId());
                if (count > 0) {
                    category.setHasChildren(true);
                } else {
                    category.setHasChildren(false);
                }

            });

        }

        return categoryList;
    }


    //    //文件导出
    @Override
    public void exportData(HttpServletResponse httpServletResponse) {

        try {
            //设置响应头信息和其他信息
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setCharacterEncoding("utf-8");


            //设置URLEncode.encode 防止中文乱码
            String fileName = URLEncoder.encode("分类数据", StandardCharsets.UTF_8);


            //设置响应头信息   Content-disposition 让文件以下载方式打开
            httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //调用mapper方法 查询所有分类
            List<Category> categoryList = categoryMapper.findAll();

            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();

            //Category convert to CategoryExcelVo
            for (Category category : categoryList) {
                CategoryExcelVo excelVo = new CategoryExcelVo();

                //BeanUtils工具类
                BeanUtils.copyProperties(category, excelVo);

                categoryExcelVoList.add(excelVo);
            }

            //调用easyExcel方法完成write
            EasyExcel.write(
                            httpServletResponse.getOutputStream(),
                            CategoryExcelVo.class)
                    .sheet("分类数据").doWrite(categoryExcelVoList);


        } catch (Exception e) {
            e.printStackTrace();
            throw new SnowLineException(ResultCodeEnum.DATA_ERROR);
        }


    }


    //文件导入
    @Override
    public void importData(MultipartFile file) {
        //监听器
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>(categoryMapper);

        try {
            EasyExcel.read(
                            file.getInputStream(),
                            CategoryExcelVo.class,
                            excelListener)
                    .sheet().doRead();


        } catch (IOException e) {
            e.printStackTrace();
            throw new SnowLineException(ResultCodeEnum.DATA_ERROR);
        }


    }
}
