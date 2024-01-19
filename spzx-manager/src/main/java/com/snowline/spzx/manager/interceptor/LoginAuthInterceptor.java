package com.snowline.spzx.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.snowline.spzx.model.entity.system.SysUser;
import com.snowline.spzx.model.vo.common.Result;
import com.snowline.spzx.model.vo.common.ResultCodeEnum;
import com.snowline.spzx.utils.AuthContextUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author Snow
 * @create 2024-01-14 1:37
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1 获取请求方式
        //如果请求是options 预检请求，直接放行
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }

        //2 从请求头里面获取token
        String token = request.getHeader("token");

        //3 如果token值为空，返回错误提示
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }

        //4 如果token不为空，拿着token查询redis
        String userInfoStr = redisTemplate.opsForValue().get("user:login" + token);

        //5 如果redis查询不到信息，返回错误提示
        if (StrUtil.isEmpty(userInfoStr)) {
            responseNoLoginInfo(response);
            return false;
        }
        //6 如果redis查询用户信息，把信息放到threadLocal
        SysUser sysUser = JSON.parseObject(userInfoStr, SysUser.class);
        AuthContextUtils.set(sysUser);

        //7 更新redis用户信息过期时间
        redisTemplate.expire("user:login" + token, 30, TimeUnit.MINUTES);

        //8 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除threadlocal里面的数据
        AuthContextUtils.remove();
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
}
