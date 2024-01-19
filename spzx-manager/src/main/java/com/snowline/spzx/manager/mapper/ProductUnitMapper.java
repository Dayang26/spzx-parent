package com.snowline.spzx.manager.mapper;

import com.snowline.spzx.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-20 0:48
 */

@Mapper
public interface ProductUnitMapper {

    //查询所有单位
    List<ProductUnit> findAll();
}
