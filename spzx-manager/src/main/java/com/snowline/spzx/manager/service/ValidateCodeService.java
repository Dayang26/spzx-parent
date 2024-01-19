package com.snowline.spzx.manager.service;

import com.snowline.spzx.model.vo.system.ValidateCodeVo;

/**
 * @author Snow
 * @create 2024-01-13 21:44
 */
public interface ValidateCodeService {


    //生成图片验证码
    ValidateCodeVo generateValidateCode();

}
