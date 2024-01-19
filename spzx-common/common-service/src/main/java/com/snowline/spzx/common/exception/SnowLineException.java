package com.snowline.spzx.common.exception;

import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author Snow
 * @create 2024-01-13 20:18
 */
@Data
public class SnowLineException extends RuntimeException {
    private Integer code;
    private String massage;
    private ResultCodeEnum resultCodeEnum;

    public SnowLineException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.massage = resultCodeEnum.getMessage();
    }

}
