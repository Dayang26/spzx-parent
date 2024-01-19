package com.snowline.spzx.manager.test;

import com.alibaba.excel.EasyExcel;
import com.snowline.spzx.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 14:52
 */
public class EasyExcelTest {

    public static void main(String[] args) {
//        read();
        write();
    }

    public static void read() {
        //定义excel位置
        String fileName = "c://02.xlsx";
        //调用方法

        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener<>();

        EasyExcel.read(
                        fileName,
                        CategoryExcelVo.class,
                        excelListener)
                .sheet().doRead();

        List<CategoryExcelVo> data = excelListener.getData();

        System.out.println(data);
    }

    public static void write() {

        List<CategoryExcelVo> list = new ArrayList<>();

        list.add(new CategoryExcelVo(1L, "数码办公", "", 0L, 1, 1));
        list.add(new CategoryExcelVo(2L, "手机", "", 1L, 1, 2));


        EasyExcel.write("c://03.xlsx", CategoryExcelVo.class)
                .sheet("分类数据").doWrite(list);
    }

}


