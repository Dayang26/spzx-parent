package com.snowline.spzx.manager.service;

import com.snowline.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 3:22
 */
public interface CategoryService {


    //分类列表，每次查询一层数据
    List<Category> findCategoryList(Long id);


    //文件导出
    void exportData(HttpServletResponse httpServletResponse);


    //文件导入
    void importData(MultipartFile multipartFile);
}
