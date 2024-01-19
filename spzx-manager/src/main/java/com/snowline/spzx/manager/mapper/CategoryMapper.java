package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.entity.product.Category;
import com.snowline.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 3:23
 */

@Mapper
public interface CategoryMapper {

    //根据id值进行查询 返回list集合
    List<Category> selectCategoryByParentId(Long id);


    //判断每一层分类是否有下一层分类 返回数量
    int selectCountByParentId(Long id);


    //    查询所有分类
    List<Category> findAll();


    //批量保存数据
    <T> void batchInsert(List<CategoryExcelVo> cachedDataList);
}
