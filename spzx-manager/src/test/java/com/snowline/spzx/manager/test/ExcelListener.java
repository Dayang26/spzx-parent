package com.snowline.spzx.manager.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Snow
 * @create 2024-01-18 14:53
 */
public class ExcelListener<T> extends AnalysisEventListener<T> {


    private List<T> data = new ArrayList<>();

    /**
     * When analysis one row trigger invoke function.
     *
     * @param t    one row value. It is same as {@link AnalysisContext#readRowHolder()}
     * @param context analysis context
     */
    @Override
    public void invoke(T t, AnalysisContext context) {
        this.data.add(t);
    }

    public List<T> getData(){
        return data;
    }


    /**
     * if have something to do after all analysis
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
