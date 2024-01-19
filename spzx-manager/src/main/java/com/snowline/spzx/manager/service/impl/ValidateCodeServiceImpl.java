package com.snowline.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.snowline.spzx.manager.service.ValidateCodeService;
import com.snowline.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Snow
 * @create 2024-01-13 21:45
 */

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    //生成图片验证码
    @Override
    public ValidateCodeVo generateValidateCode() {
        //1 通过hutool生成图片验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);

        //base64编码图片
        String imageBase64 = captcha.getImageBase64();

        //获取验证码的值
        String codeValue = captcha.getCode();

        //2 把验证码存到redis里面，设置redis的key:uuid value:验证码的值
        //设置过期时间
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:validate" + key, codeValue, 5, TimeUnit.MINUTES);

        //3 返回ValidateCodeVo对象
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(key);     //redis里面的key
        validateCodeVo.setCodeValue("data:image/png;base64,"+imageBase64);
        return validateCodeVo;
    }
}
