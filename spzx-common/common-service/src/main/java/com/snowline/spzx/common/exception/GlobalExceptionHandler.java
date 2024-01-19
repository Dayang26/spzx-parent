package com.snowline.spzx.common.exception;

import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Snow
 * @create 2024-01-13 20:06
 */

@ControllerAdvice
public class GlobalExceptionHandler {


    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
    }


    //自定义异常处理
    @ExceptionHandler(SnowLineException.class)
    @ResponseBody
    public Result error(SnowLineException e) {
        return Result.build(null, e.getResultCodeEnum());
    }
}

