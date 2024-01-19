package com.snowline.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.snowline.spzx.manager.mapper.CategoryMapper;
import com.snowline.spzx.model.vo.product.CategoryExcelVo;

import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 17:17
 * EasyExecl Listener 监听器
 */
public class ExcelListener<T> implements ReadListener<T> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


    //构造传递mapper 操作数据库
    public CategoryMapper categoryMapper;

    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }


    //从第二行开始读取，把每行读取的内容封装到t对象中
    @Override
    public void invoke(T data, AnalysisContext context) {
        //把每行数据对象data放到cacheDataList集合里面
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            //批量添加到数据库
            saveData();
            //清理list集合
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 保存数据
     */
    private void saveData() {
        categoryMapper.batchInsert((List<CategoryExcelVo>) cachedDataList);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        this.saveData();
    }
}
